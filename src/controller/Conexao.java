package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//Classe Conexão
public class Conexao {
//Detalhes da Conexão    
    private static final String DATABASE="Clinica";
    private static final String HOST="jdbc:mysql://localhost:3306/Clinica";
    private static final String DRIVER="com.mysql.jdbc.Driver";
    private static final String URL="jdbc:mysql://localhost:3306/Clinica";
    private static final String USR="root";
    private static final String PWD="";
//Metodo conectar    
    public static Connection Conectar(){
        try{
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USR, PWD);
        } 
        catch (ClassNotFoundException | SQLException e) {
            System.out.println("ERRO: " + e.getMessage());
            return null;
        }
    }
//Método Desconectar    
    public static void Desconectar (Connection con){
        try{
            if (con != null){
                con.close();
            }
        }
        catch (SQLException e){
                System.out.println("ERRO: " + e.getMessage());
                }
        }  
//Metodo Main 
    public static void main(String[] args){
        if (Conectar() != null){
            System.out.println("Conexão realizada com sucesso!");
        }
    }      
}




