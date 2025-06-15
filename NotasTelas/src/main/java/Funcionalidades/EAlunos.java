/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Funcionalidades;

/**
 *
 * @author beril
 */
public class EAlunos extends EUsuario{
    private boolean alunos_matriculados;
    private String alunos_sala, alunos_turma;
    private int qtd_disciplina;
    
    public EAlunos(){
        
    }

    //public EAlunos(String nome, String cpf, String endereco, String dataNasc, String sexo, int usuario_id) {
    //    super(getNome(), getCpf(), getEndereco(), getDataNasc(), getSexo(), getUsuario_id());
   // }

    public boolean isAlunos_matriculados() {
        return alunos_matriculados;
    }

    public void setAlunos_matriculados(boolean alunos_matriculados) {
        this.alunos_matriculados = alunos_matriculados;
    }

    public String getAlunos_sala() {
        return alunos_sala;
    }

    public void setAlunos_sala(String alunos_sala) {
        this.alunos_sala = alunos_sala;
    }

    public String getAlunos_turma() {
        return alunos_turma;
    }

    public void setAlunos_turma(String alunos_turma) {
        this.alunos_turma = alunos_turma;
    }

    public int getQtd_disciplina() {
        return qtd_disciplina;
    }

    public void setQtd_disciplina(int qtd_disciplina) {
        this.qtd_disciplina = qtd_disciplina;
    }
}
