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
       //TelaInstituicao.montarTelaInstituicao(); //corrigir o gerar arquivo
       //TelaNotas.montarTelaNotas(); -> fazer o campo filtro e arrumar para o nome do aluno aparecer, o professor e a disciplina
       //TelaProfessores.montarTelaProfessor(); // fazer o campo para filtrar 
       //TelaNotas.montarTelaNotas();
       TelaFrequencias.MontarTelaFrequencia();
       TelaPrincipal.montarTelaInicial();
    }
    
    
}
