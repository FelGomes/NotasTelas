/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tela;
<<<<<<< HEAD
import Entidades.TelaBoletim;
import Entidades.TelaInstituicao;
import Entidades.TelaNotas;
import Entidades.TelaProfessores;
import Entidades.Usuarios;
=======
import Entidades.*;
>>>>>>> f0c0f81978cad560e2259913e32b138564cc08db
import java.io.IOException;
import java.sql.SQLException;

/**
 *
 * @author felipe
 */
public class App {
    
    public static void main(String[] args) throws IOException, SQLException {
        
<<<<<<< HEAD
       //Usuarios.montarTelaUsuario(); deu certo
       //TelaBoletim.montarTelaVisualizacao(); -> deu certo pra listar
       //TelaInstituicao.montarTelaInstituicao(); // faltar o filtro e corrigir o gerar arquivo
<<<<<<< HEAD
       //TelaNotas.montarTelaNotas(); -> fazer o campo filtro e arrumar para o nome do aluno aparecer, o professor e a disciplina
       //TelaProfessores.montarTelaProfessor(); // fazer o campo para filtrar 
=======
       //TelaNotas.montarTelaNotas();
       TelaProfessores.montarTelaProfessor();
>>>>>>> Felipe
=======
        TelaPrincipal.montarTelaInicial();
>>>>>>> f0c0f81978cad560e2259913e32b138564cc08db
    }
    
    
}
