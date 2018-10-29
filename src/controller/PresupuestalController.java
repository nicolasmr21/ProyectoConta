package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.util.Callback;
import javafx.util.StringConverter;

public class PresupuestalController implements Initializable {
    @FXML
    private MainController main;
    
    @FXML
    private BorderPane root;
    
	private final ObservableList<Data> data =
	        FXCollections.observableArrayList(
	                new Data(5500.,74500.),
	                new Data(7000.,85000.),
	                new Data(5000.,70000.),
	                new Data(6500.,82000.),
	                new Data(7500.,96000.),
	                new Data(8000.,100000.),
	                new Data(6000.,82500.)


	        );	
	
	@FXML
    private TableView<Data> table;


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		  // create edtiable table
        table = new TableView<Data>();
        table.setEditable(true);

        // column 1 contains numbers
        TableColumn<Data, Number> number1Col = new TableColumn<>("X");
        number1Col.setMinWidth(350);
        number1Col.setCellValueFactory( cellData -> cellData.getValue().number1Property());
        number1Col.setCellFactory( createNumberCellFactory());

        // column 2 contains numbers
        TableColumn<Data, Number> number2Col = new TableColumn<>("Y");
        number2Col.setMinWidth(350);
        number2Col.setCellValueFactory( cellData -> cellData.getValue().number2Property());
        number2Col.setCellFactory( createNumberCellFactory());

        // add columns & data to table
        table.setItems(data);
        table.getColumns().addAll( number1Col, number2Col);




        // switch to edit mode on keypress
        // this must be KeyEvent.KEY_PRESSED so that the key gets forwarded to the editing cell; it wouldn't be forwarded on KEY_RELEASED
        table.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {

                if( event.getCode() == KeyCode.ENTER) {
//                  event.consume(); // don't consume the event or else the values won't be updated;
                    return;
                }

                // switch to edit mode on keypress, but only if we aren't already in edit mode
                if( table.getEditingCell() == null) {
                    if( event.getCode().isLetterKey() || event.getCode().isDigitKey()) {  

                        TablePosition focusedCellPosition = table.getFocusModel().getFocusedCell();
                        table.edit(focusedCellPosition.getRow(), focusedCellPosition.getTableColumn());

                    }
                }

            }
        });

        table.addEventFilter(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {

                if( event.getCode() == KeyCode.ENTER) {


                    // move focus & selection
                    // we need to clear the current selection first or else the selection would be added to the current selection since we are in multi selection mode 
                    TablePosition pos = table.getFocusModel().getFocusedCell();

                    if (pos.getRow() == -1) {
                        table.getSelectionModel().select(0);
                    } 
                    // add new row when we are at the last row
                    else if (pos.getRow() == table.getItems().size() -1) {
                        addRow();
                    } 
                    // select next row, but same column as the current selection
                    else if (pos.getRow() < table.getItems().size() -1) {
                        table.getSelectionModel().clearAndSelect( pos.getRow() + 1, pos.getTableColumn());
                    }


                }

            }
        });     

        // single cell selection mode
        table.getSelectionModel().setCellSelectionEnabled(true);

        // add row index column as 1st column
        // -------------------------------------
        TableColumn<Data, Data> indexCol = new TableColumn<Data, Data>("#");

        indexCol.setCellFactory(new Callback<TableColumn<Data, Data>, TableCell<Data, Data>>() {
            @Override public TableCell<Data, Data> call(TableColumn<Data, Data> param) {
                return new TableCell<Data, Data>() {
                    @Override protected void updateItem(Data item, boolean empty) {
                        super.updateItem(item, empty);

                        if (this.getTableRow() != null) {

                            int index = this.getTableRow().getIndex();

                            if( index < table.getItems().size()) {
                                int rowNum = index + 1;
                                setText( String.valueOf(rowNum));
                            } else {
                                setText("");
                            }

                        } else {
                            setText("");
                        }

                    }
                };
            }
        });

        table.getColumns().add( 0, indexCol); // number column is at index 0

        // allow multi selection
        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        // buttons
        // --------------------------------------------
        FlowPane buttonBar = new FlowPane();
        buttonBar.setAlignment(Pos.CENTER);

        // add new row button
        Button addButton = new Button( "Add");
        addButton.setOnAction(e -> {
            addRow();
        });
        addButton.setFocusTraversable(false);// don't let it get the focus or else the table would lose it when we click the button and we's have to request the focus on the table in the event handler

        // remove selected rows button
        Button removeButton = new Button( "Remove");
        removeButton.setOnAction(e -> {
            removeSelectedRows();
        });
        removeButton.setFocusTraversable(false);// don't let it get the focus or else the table would lose it when we click the button and we's have to request the focus on the table in the event handler

        // remove selected rows button
        Button calcButton = new Button( "Calcular");
        calcButton.setOnAction(e -> {
        	
        	double[] a = new double[data.size()];
        	double[] b = new double[data.size()];
        	
        	for (int i = 0; i < data.size(); i++) {
				a[i] = data.get(i).getNumber1();
				b[i] = data.get(i).getNumber2();
			}
        	
        	int iMayor = 0;
        	int iMenor = 0;
        	
        	for (int i = 0; i < data.size(); i++) {
        		double x = data.get(i).getNumber1();
        		double xMayor = data.get(iMayor).getNumber1();
        		double xMenor = data.get(iMenor).getNumber1();
				if(x>xMayor) {
					iMayor = i;
				}
				if(x<xMenor) {
					iMenor = i;
				}
			}
        	double xMayor = data.get(iMayor).getNumber1();
        	double xMenor = data.get(iMenor).getNumber1();
        	double yMayor = data.get(iMayor).getNumber2();
        	double yMenor = data.get(iMenor).getNumber2();

        	try {
				main.crearGraficos(a, b, xMayor, xMenor, yMayor, yMenor) ;
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	
        });
        
        calcButton.setFocusTraversable(false);
        
        buttonBar.getChildren().addAll( addButton, removeButton, calcButton);

        // add nodes to stage
        root.setCenter(table);
        root.setBottom(buttonBar);
	}
	
	 /**
     * Insert a new default row to the table, select a cell of it and scroll to it. 
     */
    public void addRow() {

        // get current position
        TablePosition pos = table.getFocusModel().getFocusedCell();

        // clear current selection
        table.getSelectionModel().clearSelection();

        // create new record and add it to the model
        Data data = new Data(0d,0d);
        table.getItems().add( data);

        // get last row
        int row = table.getItems().size() - 1;
        table.getSelectionModel().select( row, pos.getTableColumn());

        // scroll to new row
        table.scrollTo( data);

    }
    
    /**
     * Remove all selected rows.
     */
    public void removeSelectedRows() {

        table.getItems().removeAll(table.getSelectionModel().getSelectedItems());

        // table selects by index, so we have to clear the selection or else items with that index would be selected 
        table.getSelectionModel().clearSelection();


    }
    
    /**
     * Number cell factory which converts strings to numbers and vice versa.
     * @return
     */
    private Callback<TableColumn<Data, Number>, TableCell<Data, Number>>  createNumberCellFactory() {

        Callback<TableColumn<Data, Number>, TableCell<Data, Number>> factory = TextFieldTableCell.forTableColumn( new StringConverter<Number>() {

            public Number fromString(String string) {
                return Double.parseDouble(string);
            }

            public String toString(Number object) {
                return object.toString();
            }
        });

        return factory;
    }
    
    
	
	public static class Data {

        private final SimpleDoubleProperty number1;
        private final SimpleDoubleProperty number2;

        private Data( Double number1, Double number2) {
            this.number1 = new SimpleDoubleProperty(number1);
            this.number2 = new SimpleDoubleProperty(number2);
        }

        public final DoubleProperty number1Property() {
            return this.number1;
        }

        public final double getNumber1() {
            return this.number1Property().get();
        }

        public final void setNumber1(final double number1) {
            this.number1Property().set(number1);
        }

        public final DoubleProperty number2Property() {
            return this.number2;
        }

        public final double getNumber2() {
            return this.number2Property().get();
        }

        public final void setNumber2(final double number2) {
            this.number2Property().set(number2);
        }


    }


	 public void init(MainController m) {
			main = m;
		}

}
