package sample;

import elements.SGDBTable;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import objects.*;

import java.util.Vector;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {

        TabPane tabPane = new TabPane();

        Tab tab1 = new Tab("Clientes");
        Tab tab2 = new Tab("Motoristas");
        Tab tab3 = new Tab("Reservas");
        Tab tab4 = new Tab("Locações");
        Tab tab5 = new Tab("Veiculos");
        Tab tab6 = new Tab("Tipos de Veículos");
        Tab tab7 = new Tab("Filiais");

        tab1.setClosable(false);
        tab2.setClosable(false);
        tab3.setClosable(false);
        tab4.setClosable(false);
        tab5.setClosable(false);
        tab6.setClosable(false);
        tab7.setClosable(false);

        tabPane.getTabs().add(tab1);
        tabPane.getTabs().add(tab2);
        tabPane.getTabs().add(tab3);
        tabPane.getTabs().add(tab4);
        tabPane.getTabs().add(tab5);
        tabPane.getTabs().add(tab6);
        tabPane.getTabs().add(tab7);

        // Tabela de Cliente
        SGDBTable<Cliente> dataTable1 = new SGDBTable<Cliente>();
        Vector<String> colNames = new Vector<String>();
        colNames.add("Cod_cliente");
        colNames.add("Nome");
        colNames.add("Endereco");
        dataTable1.setColumNames(colNames);
        tab1.setContent(dataTable1.getTable());

        SGDBTable<Motorista> dataTable2 = new SGDBTable<Motorista>();
        colNames = new Vector<>();
        colNames.add("Cod_motorista");
        colNames.add("Cod_cliente");
        colNames.add("Num_habili");
        colNames.add("Ident_motorista");
        colNames.add("Vencimento_habili");
        dataTable2.setColumNames(colNames);
        tab2.setContent(dataTable2.getTable());

        SGDBTable<Reserva> dataTable3 = new SGDBTable<Reserva>();
        colNames = new Vector<>();
        colNames.add("Cod_reserva");
        colNames.add("Tipo");
        colNames.add("Cod_filial_dest");
        colNames.add("Cod_filial_orig");
        colNames.add("Cod_cliente");
        colNames.add("Data_retirada");
        colNames.add("Data_entrega");
        dataTable3.setColumNames(colNames);
        tab3.setContent(dataTable3.getTable());

        SGDBTable<Locacao> dataTable4 = new SGDBTable<Locacao>();
        colNames = new Vector<>();
        colNames.add("Cod_locacao");
        colNames.add("Cod_placa");
        colNames.add("Cod_filial_dest");
        // tem que ver como que faz drop down dentro de tabela
        // ver pq é uma lista
//        colNames.add("Cod_motorista");
        colNames.add("Data_entrega");
        dataTable4.setColumNames(colNames);
        tab4.setContent(dataTable4.getTable());

        SGDBTable<Veiculo> dataTable5 = new SGDBTable<Veiculo>();
        colNames = new Vector<>();
        colNames.add("Cod_placa");
        colNames.add("Cod_tipo");
        colNames.add("Cod_filial_atual");
        colNames.add("Num_chassi");
        colNames.add("Num_motor");
        colNames.add("Cor");
        colNames.add("Km_atual");
        colNames.add("revisao_pendente");
        dataTable5.setColumNames(colNames);
        tab5.setContent(dataTable5.getTable());

        SGDBTable<Tipo_Veiculo> dataTable6 = new SGDBTable<>();
        colNames = new Vector<>();
        colNames.add("Cod_tipo");
        colNames.add("Horas_limpeza");
        colNames.add("Horas_revisao");
        dataTable6.setColumNames(colNames);
        tab6.setContent(dataTable6.getTable());

        SGDBTable<Filial> dataTable7 = new SGDBTable<>();
        colNames = new Vector<>();
        colNames.add("Cod_filial");
        colNames.add("Localizacao");
        dataTable7.setColumNames(colNames);
        tab7.setContent(dataTable7.getTable());

        tabPane.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Tab>() {
                    @Override
                    public void changed(ObservableValue<? extends Tab> observableValue, Tab tab, Tab t1) {
                        System.out.print("Mudou de aba: ");
                        System.out.println(tab.getText());
                        System.out.print("para: ");
                        System.out.println(t1.getText());
                    }
                }
        );

        VBox vBox = new VBox(tabPane);
        Scene scene = new Scene(vBox);

        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX App");

        primaryStage.show();
    }

}


//    List linList = new ArrayList();
//    Cliente newCli1 = new Cliente();
//        newCli1.setCod_cliente(0);
//                newCli1.setEndereco("Rua1");
//                newCli1.setNome("n1");
//                linList.add(newCli1);
//                Cliente newCli2 = new Cliente();
//                newCli2.setCod_cliente(1);
//                newCli2.setEndereco("Rua2");
//                newCli2.setNome("n2");
//                linList.add(newCli2);
//                Cliente newCli3 = new Cliente();
//                newCli3.setCod_cliente(2);
//                newCli3.setEndereco("Rua3");
//                newCli3.setNome("n3");
//                linList.add(newCli3);
//                dataTable1.setTableData(linList);