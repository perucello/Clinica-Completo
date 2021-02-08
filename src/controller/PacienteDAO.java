package controller;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Paciente;

public class PacienteDAO {

//    public static List<Paciente> list() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//    
    private final Connection con;
    private PreparedStatement cmd;
    
    public PacienteDAO(){
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
    
    public int atualizar(Paciente p){
        try{
            String sql = "update paciente set nome=?, peso=?, altura=? where id=? ";
            
            cmd = con.prepareStatement(sql);
            cmd.setString(1, p.getNome());
            cmd.setFloat(2, p.getPeso());
            cmd.setFloat(3, p.getAltura());
            cmd.setInt(4, p.getId());
            
            if (cmd.executeUpdate() > 0){
                return p.getId();
            }
            else{
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
        catch (SQLException e){
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
        catch (SQLException e){
            System.out.println("ERRO: " + e.getMessage());
            return null;
        }
        finally{
            Conexao.Desconectar(con);
        }
    }

    public int deletar(Paciente p){
        try{
            String sql = "delete from paciente where id = ?";
            
            cmd = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            cmd.setInt(1, p.getId());
            cmd.execute();
            JOptionPane.showMessageDialog(null, "Excluido com sucesso");
        }
        catch (HeadlessException | SQLException e){
            System.out.println("ERRO: " + e.getMessage());
            return -1;
        }
        finally{
            Conexao.Desconectar(con);
        }
        return 0;
    }
      


}
