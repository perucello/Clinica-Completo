package controller;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Paciente;

public class PacienteDAOListar {

    public static List<Paciente> list() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private Connection con;
    private PreparedStatement cmd;
    
    public PacienteDAOListar(){
        this.con = Conexao.Conectar();
    }
   
    public List<Paciente> listar(){
        try{
            String sql = "select * from paciente order by id";
            cmd = con.prepareStatement(sql);
            ResultSet rs = cmd.executeQuery();
            
            List<Paciente> lista = new ArrayList<>();
            while(rs.next()){
                
                Paciente p = new Paciente();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setPeso(rs.getFloat("peso"));
                p.setAltura(rs.getFloat("altura"));
                
                lista.add(p);
            }
            return lista;
        }
        catch (Exception e){
            System.out.println("ERRO: " + e.getMessage());
            return null;
        }   
        finally{
            Conexao.Desconectar(con);
        }
            
    }
    
    public List<Paciente> pesquisarPorNome(String nome){
        try{
            String sql = "select * from paciente where nome like ? order by nome;";
            cmd = con.prepareStatement(sql);
            cmd.setString(1, "%" + nome + "%");
            
            ResultSet rs = cmd.executeQuery();
            List<Paciente> lista = new ArrayList<>();
            
            while(rs.next()){
                Paciente p = new Paciente();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setPeso(rs.getFloat("peso")); 
                p.setAltura(rs.getFloat("altura"));
                
                lista.add(p);
            }
            return lista;
        }
        catch (Exception e){
            System.out.println("ERRO: " + e.getMessage());
            return null;
        }
        finally{
            Conexao.Desconectar(con);
        }
    }
}
