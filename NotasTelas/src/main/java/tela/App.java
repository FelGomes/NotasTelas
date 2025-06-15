/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tela;
import Entidades.TelaBoletim;
import Entidades.TelaInstituicao;
import Entidades.TelaNotas;
import Entidades.TelaProfessores;
import Entidades.Usuarios;
import java.io.IOException;
import java.sql.SQLException;

/**
 *
 * @author felipe
 */
public class App {
    
    public static void main(String[] args) throws IOException, SQLException {
        
       //Usuarios.montarTelaUsuario(); deu certo
       //TelaBoletim.montarTelaVisualizacao(); -> deu certo pra listar
       //TelaInstituicao.montarTelaInstituicao(); // faltar o filtro e corrigir o gerar arquivo
       //TelaNotas.montarTelaNotas();
       TelaProfessores.montarTelaProfessor();
    }
    
    
}
