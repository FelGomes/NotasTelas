package Funcionalidades;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author felipe Ferreira
 * @since 13/04 at 20:20
 */
public class EInstituicao {
    
    private float inst_nivel;
    private String inst_nome, inst_endereco, inst_cidade, inst_uf, inst_escolaridade;
    
    
    
    
    
    /**Instituicao(String inst_nome, String inst_endereco, String inst_cidade, String inst_uf, String inst_escolaridade, float inst_nivel){
        this.inst_nome = inst_nome;
        this.inst_endereco = inst_endereco;
        this.inst_cidade = inst_cidade;
        this.inst_uf = inst_uf;
        this.inst_escolaridade = inst_escolaridade;
        this.inst_nivel = inst_nivel;
    }*/

    public float getInst_nivel() {
        return inst_nivel;
    }

    public void setInst_nivel(float inst_nivel) {
        this.inst_nivel = inst_nivel;
    }

    public String getInst_nome() {
        return inst_nome;
    }

    public void setInst_nome(String inst_nome) {
        this.inst_nome = inst_nome;
    }

    public String getInst_endereco() {
        return inst_endereco;
    }

    public void setInst_endereco(String inst_endereco) {
        this.inst_endereco = inst_endereco;
    }

    public String getInst_cidade() {
        return inst_cidade;
    }

    public void setInst_cidade(String inst_cidade) {
        this.inst_cidade = inst_cidade;
    }

    public String getInst_uf() {
        return inst_uf;
    }

    public void setInst_uf(String inst_uf) {
        this.inst_uf = inst_uf;
    }

    public String getInst_escolaridade() {
        return inst_escolaridade;
    }

    public void setInst_escolaridade(String inst_escolaridade) {
        this.inst_escolaridade = inst_escolaridade;
    }
    
}