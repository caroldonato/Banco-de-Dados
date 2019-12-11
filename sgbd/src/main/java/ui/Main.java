package ui;

import elements.SGDBTable;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import entities.*;
import queries.Deletion;
import queries.Insertion;
import queries.Query;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.*;
import java.util.Vector;

public class Main extends Application {

    private Query query;

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

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("LocadoraVeiculos-PU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        while(this.query == null)
            this.query = new Query(entityManager);
        this.createClienteTab(tab1);
        this.createMotoristaTab(tab2);
        this.createReservasTab(tab3);
        this.createLocacoesTab(tab4);
        this.createVeiculosTab(tab5);
        this.createTiposVeicTab(tab6);
        this.createFiliaisTab(tab7);

        //todo: adcionar algo para atualizar dinâmicamente as linhas
//        Insertion inserts = new Insertion(entityManager);
//        Deletion deletions = new Deletion(entityManager);
//
//

        tabPane.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Tab>() {
                    @Override
                    public void changed(ObservableValue<? extends Tab> observableValue, Tab tab, Tab t1) {
                        System.out.print("Mudou de aba: [");
                        System.out.print(tab.getText());
                        System.out.print("] para: [");
                        System.out.print(t1.getText());
                        System.out.println("]");
                        Tab tab1 = new Tab("Clientes");
                        Tab tab2 = new Tab("Motoristas");
                        Tab tab3 = new Tab("Reservas");
                        Tab tab4 = new Tab("Locações");
                        Tab tab5 = new Tab("Veiculos");
                        Tab tab6 = new Tab("Tipos de Veículos");
                        Tab tab7 = new Tab("Filiais");
                        if (t1.getText().equals("Clientes"))
                            createClienteTab(t1);
                        else if (t1.getText().equals("Motoristas"))
                            createMotoristaTab(t1);
                        else if (t1.getText().equals("Reservas"))
                            createReservasTab(t1);
                        else if (t1.getText().equals("Locações"))
                            createLocacoesTab(t1);
                        else if (t1.getText().equals("Veiculos"))
                            createVeiculosTab(t1);
                        else if (t1.getText().equals("Tipos de Veículos"))
                            createTiposVeicTab(t1);
                        else if (t1.getText().equals("Filiais"))
                            createFiliaisTab(t1);

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

        primaryStage.showAndWait();

//        deletions.clearAllTables();
//        inserts.populateTables();
          entityManager.close();
          entityManagerFactory.close();
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

    private void createFiliaisTab(Tab tab7) {
        SGDBTable<Filial> dataTable7 = new SGDBTable<>();
        Vector<String> colNames = new Vector<>();
        colNames.add("Cod_filial");
        colNames.add("Localizacao");
        dataTable7.setColumNames(colNames);
        dataTable7.setTableData(query.queryAllFiliais());
        tab7.setContent(dataTable7.getTable());
    }

    private void createTiposVeicTab(Tab tab6) {
        SGDBTable<Tipo_Veiculo> dataTable6 = new SGDBTable<>();
        Vector<String> colNames = new Vector<>();
        colNames.add("Cod_tipo");
        colNames.add("Horas_limpeza");
        colNames.add("Horas_revisao");
        dataTable6.setColumNames(colNames);
        dataTable6.setTableData(query.queryAllTiposVeiculo());
        tab6.setContent(dataTable6.getTable());
    }

    private void createVeiculosTab(Tab tab5) {
        SGDBTable<Veiculo> dataTable5 = new SGDBTable<Veiculo>();
        Vector<String> colNames = new Vector<>();
        colNames.add("Cod_placa");
        colNames.add("Cod_tipo");
        colNames.add("Cod_filial_atual");
        colNames.add("Num_chassi");
        colNames.add("Num_motor");
        colNames.add("Cor");
        colNames.add("Km_atual");
        colNames.add("revisao_pendente");
        dataTable5.setColumNames(colNames);
        dataTable5.setTableData(query.queryAllVeiculos());
        tab5.setContent(dataTable5.getTable());

    }

    private void createLocacoesTab(Tab tab4) {
        SGDBTable<Locacao> dataTable4 = new SGDBTable<Locacao>();
        Vector<String> colNames = new Vector<>();
        colNames.add("Cod_locacao");
        colNames.add("Cod_placa");
        colNames.add("Cod_filial_dest");
        // tem que ver como que faz drop down dentro de tabela
        // ver pq é uma lista
//        colNames.add("Cod_motorista");
        colNames.add("Data_entrega");
        dataTable4.setColumNames(colNames);
        dataTable4.setTableData(query.queryAllLocacoes());
        tab4.setContent(dataTable4.getTable());
    }

    private void createReservasTab(Tab tab3) {
        SGDBTable<Reserva> dataTable3 = new SGDBTable<Reserva>();
        Vector<String> colNames = new Vector<>();
        colNames.add("Cod_reserva");
        colNames.add("Tipo");
        colNames.add("Cod_filial_dest");
        colNames.add("Cod_filial_orig");
        colNames.add("Cod_cliente");
        colNames.add("Data_retirada");
        colNames.add("Data_entrega");
        dataTable3.setColumNames(colNames);
        dataTable3.setTableData(query.queryAllReservas());
        tab3.setContent(dataTable3.getTable());
    }

    private void createMotoristaTab(Tab tab2) {
        SGDBTable<Motorista> dataTable2 = new SGDBTable<Motorista>();
        Vector<String> colNames = new Vector<>();
        colNames.add("Cod_motorista");
        colNames.add("Cod_cliente");
        colNames.add("Num_habili");
        colNames.add("Ident_motorista");
        colNames.add("Vencimento_habili");
        dataTable2.setColumNames(colNames);
        dataTable2.setTableData(query.queryAllMotoristas());
        tab2.setContent(dataTable2.getTable());
    }

    private void createClienteTab(Tab tab1) {
        SGDBTable<Cliente> dataTable1 = new SGDBTable<Cliente>();
        Vector<String> colNames = new Vector<String>();
        colNames.add("Cod_cliente");
        colNames.add("Nome");
        colNames.add("Endereco");
        dataTable1.setColumNames(colNames);
        dataTable1.setTableData(this.query.queryAllClientes());
        tab1.setContent(dataTable1.getTable());
    }
}