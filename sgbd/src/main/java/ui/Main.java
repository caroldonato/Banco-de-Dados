package ui;

import elements.SGDBTable;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import entities.*;

import javax.swing.*;
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
                        System.out.print("Mudou de aba: [");
                        System.out.print(tab.getText());
                        System.out.print("] para: [");
                        System.out.print(t1.getText());
                        System.out.println("]");
                    }
                }
        );
        // criando barra de menu
        MenuBar menuBar = new MenuBar();
        // criando menus da barra de menus
        Menu menuOpt = new Menu("Opções");

        MenuItem menuItemInsert = new MenuItem("Inserir");
        MenuItem menuItemRemove = new MenuItem("Remover");
        MenuItem menuItemConsul = new MenuItem("Consultar");

        menuItemInsert.setOnAction(event -> {
            System.out.println("Janela de inserção aberta");
            this.handleInsert(new Stage());
            System.out.println("Janela de inserção fechada");
        });
        menuItemRemove.setOnAction(event -> {
            System.out.println("Janela de remoção aberta");
            this.handleRemove(new Stage());
            System.out.println("Janela de remoção fechada");
        });
        menuItemConsul.setOnAction(event -> {
            System.out.println("Janela de consultas aberta");
            this.handleConsult(new Stage());
            System.out.println("Janela de consultas fechada");
        });

        menuOpt.getItems().add(menuItemInsert);
        menuOpt.getItems().add(menuItemRemove);
        menuOpt.getItems().add(menuItemConsul);

        menuBar.getMenus().add(menuOpt);

        VBox vBox = new VBox(menuBar);
        vBox.getChildren().add(tabPane);
        Scene scene = new Scene(vBox);

        primaryStage.setScene(scene);
        primaryStage.setTitle("LOCADORA DE VEÍCULOS");

        primaryStage.show();
    }

    private void handleConsult(Stage primaryStage) {
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

        //criando aba de criação de cliente
        Button newb = new Button("Consultar");
        tab1.setContent(newb);

        VBox total = new VBox(tabPane);
        Scene scene = new Scene(total);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Consulta de entrada");

        primaryStage.showAndWait();
    }

    private void handleRemove(Stage primaryStage) {
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

        //criando aba de criação de cliente
        Button newb = new Button("Remover");
        tab1.setContent(newb);

        VBox total = new VBox(tabPane);
        Scene scene = new Scene(total);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Remoção de entrada");

        primaryStage.showAndWait();
    }

    public void handleInsert(Stage primaryStage)
    {
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

        //criando aba de criação de cliente
        Button newb = new Button("Criar");
        tab1.setContent(newb);

        VBox total = new VBox(tabPane);
        Scene scene = new Scene(total);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Criação de entrada");

        primaryStage.showAndWait();
    }

}