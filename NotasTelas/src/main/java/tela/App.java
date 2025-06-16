/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tela;
import Entidades.*;
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
       //TelaInstituicao.montarTelaInstituicao(); deu certo

       //TelaNotas.montarTelaNotas(); -> fazer o campo filtro e arrumar para o nome do aluno aparecer, 
      
       //TelaNotas.montarTelaNotas(); -> certo
       TelaDiario.montarTelaDiario();
       //TelaProfessores.montarTelaProfessor(); certo
       //TelaNotas.montarTelaNotas();

       //TelaFrequencias.montarTelaFrequencia();
       TelaPrincipal.montarTelaInicial();

       //TelaFrequencias.MontarTelaFrequencia();
       //TelaPrincipal.montarTelaInicial();


    }
    
    
}
