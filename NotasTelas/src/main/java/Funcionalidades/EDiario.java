
package Funcionalidades;

/**
 *
 * @author Kauã Luiz
 */
public class EDiario {
    private int diarios_id;
    private String diarios_local;
    private String diarios_disciplinas;
    private int qtd_alunos;
    private int fk_diarios_professores_;
    private int fk_diarios_alunos_;
    
    // GETTERS E SETTERS 
    public int getDiarios_id(){
        return diarios_id;
    }
    public void setDiarios_id(int diarios_id){
        this.diarios_id = diarios_id;
    }
    public String getDiarios_local() {
        return diarios_local;
    }
    public void setDiarios_local(String diarios_local){
        this.diarios_local = diarios_local;
    }
    public String getDiarios_disciplinas(){
        return diarios_disciplinas;
    }
    public void setDiarios_disciplinas(String dirios_disciplinas){
        this.diarios_disciplinas = diarios_disciplinas;
    }
    public int getQtd_alunos(){
        return qtd_alunos;
    }
    public void setQtd_alunos(int Qtd_alunos){
        this.qtd_alunos = qtd_alunos;
    }
    public int getFk_diarios_professores_(){
        return fk_diarios_professores_;
    }
    public void setFk_diarios_professores_(int Fk_diarios_professores_){
        this.fk_diarios_professores_ = fk_diarios_professores_;
    }
    public int getFk_diarios_alunos_(){
        return fk_diarios_alunos_;
    }
    public void setFk_diarios_alunos_(int fk_diarios_alunos_){
        this.fk_diarios_alunos_ = fk_diarios_alunos_;
    }
    
    
}
