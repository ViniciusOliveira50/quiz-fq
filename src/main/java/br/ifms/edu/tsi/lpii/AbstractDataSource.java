package br.ifms.edu.tsi.lpii;

import com.google.gson.Gson;
import java.io.File;
import java.io.IOException;


/**
*
* @author Vinicius de Oliveira
*/
public abstract class AbstractDataSource<T> implements DataSourceInterface<T> {
    protected Gson gson;
    protected File baseDir;

    public AbstractDataSource() {
        this.gson = new Gson();
        // Define um diretório base padrão, pode ser sobrescrito pelas subclasses
        this.baseDir = new File("."); 
    }


}


