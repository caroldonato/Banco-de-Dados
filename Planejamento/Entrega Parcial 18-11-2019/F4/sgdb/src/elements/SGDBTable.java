package elements;

import javafx.collections.FXCollections;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;
import java.util.Vector;

public class SGDBTable<T> {
    public TableView<T> table;

    public SGDBTable() {
        this.table = new TableView<T>();
        this.table.setPlaceholder(new Label("Sem linhas para expor!"));
    }

    public SGDBTable(TableView table) {
        //! verificar se são do mesmo tipo
        this.table = table;
        this.table.setPlaceholder(new Label("Sem linhas para expor!"));
    }

    // Preenche as linhas da tebela
    // Precisa de um lista de conteúdos
    public void setTableData(List dataList) {
        this.table.setItems(FXCollections.observableList(dataList));
    }

    // Adiciona os nomes das colunas da tabela
    // Para cada coluna, a classe que será contida na tabela deve ter o atributo de mesmo nome (em lowcase)
    public void setColumNames(Vector<String> columNameList) {
        for (String colName : columNameList) {
            TableColumn newColumn = new TableColumn(colName);
            newColumn.setCellValueFactory(new PropertyValueFactory(colName.toLowerCase()));
            this.table.getColumns().add(newColumn);
        }
    }

    public TableView getTable() {
        return table;
    }
}
