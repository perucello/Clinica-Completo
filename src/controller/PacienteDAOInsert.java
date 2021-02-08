package controller;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Paciente;

public class PacienteDAOInsert {

    public static List<Paciente> list() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
    private Connection con;
    private PreparedStatement cmd;
    
    public PacienteDAOInsert(){
        this.con = Conexao.Conectar();
        
    }
    
    public int inserir(Paciente p){
        try{
            String sql = "insert into paciente (nome, peso, altura) values (?, ?, ?);";
            
            cmd = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            cmd.setString(1, p.getNome());
            cmd.setFloat(2, p.getPeso());
            cmd.setFloat(3, p.getAltura());
            
            if (cmd.executeUpdate() > 0){
                ResultSet rs = cmd.getGeneratedKeys();
                return (rs.next()) ? rs.getInt(1): -1;
            }else{
                return -1;
            }
        }
        catch (SQLException e){
            System.out.println("ERRO: " + e.getMessage());
            return -1;
        }
        finally{
            Conexao.Desconectar(con);
        }
    }

}
