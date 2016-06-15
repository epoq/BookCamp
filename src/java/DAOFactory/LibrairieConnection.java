
package DAOFactory;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.InitialContext;

import javax.naming.NamingException;
import javax.sql.DataSource;


public  class LibrairieConnection {
        private static final String POOL_CNT = "jdbc/librairie";
   
  public  static DataSource getDataSource() throws NamingException{
        InitialContext ic = new InitialContext();
        return(DataSource) ic.lookup(POOL_CNT);
      
    }
}
