
package Entidades;

/**
 *
 * @author Kauã Luiz
 */
import Funcionalidades.EDiario;
import Funcionalidades.PDiario;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.*;

public class TelaDiario {
    public static void montarTelaDiario() throws IOException, SQLException {

        JFrame janela = new JFrame("Gerenciar Diário");
        janela.setBounds(200, 150, 650, 550);
        janela.setLayout(null);

        // Labels e campos
        JLabel labelLocal = new JLabel("Local:");
        labelLocal.setBounds(20, 20, 80, 25);
        janela.add(labelLocal);

        JTextField campoLocal = new JTextField();
        campoLocal.setBounds(100, 20, 200, 25);
        janela.add(campoLocal);

        JLabel labelDisciplinas = new JLabel("Disciplinas:");
        labelDisciplinas.setBounds(20, 60, 80, 25);
        janela.add(labelDisciplinas);

        JTextField campoDisciplinas = new JTextField();
        campoDisciplinas.setBounds(100, 60, 200, 25);
        janela.add(campoDisciplinas);

        JLabel labelQtdAlunos = new JLabel("Qtd Alunos:");
        labelQtdAlunos.setBounds(20, 100, 80, 25);
        janela.add(labelQtdAlunos);

        JTextField campoQtdAlunos = new JTextField();
        campoQtdAlunos.setBounds(100, 100, 200, 25);
        janela.add(campoQtdAlunos);

        JLabel labelFkProfessor = new JLabel("ID Professor:");
        labelFkProfessor.setBounds(320, 20, 100, 25);
        janela.add(labelFkProfessor);

        JTextField campoFkProfessor = new JTextField();
        campoFkProfessor.setBounds(420, 20, 200, 25);
        janela.add(campoFkProfessor);

        JLabel labelFkAluno = new JLabel("ID Aluno:");
        labelFkAluno.setBounds(320, 60, 100, 25);
        janela.add(labelFkAluno);

        JTextField campoFkAluno = new JTextField();
        campoFkAluno.setBounds(420, 60, 200, 25);
        janela.add(campoFkAluno);

        // Botões
        JButton botaoSalvar = new JButton("SALVAR");
        botaoSalvar.setBounds(320, 100, 90, 25);
        janela.add(botaoSalvar);

        JButton botaoAlterar = new JButton("ALTERAR");
        botaoAlterar.setBounds(420, 100, 90, 25);
        botaoAlterar.setEnabled(false);
        janela.add(botaoAlterar);

        JButton botaoExcluir = new JButton("EXCLUIR");
        botaoExcluir.setBounds(520, 100, 90, 25);
        botaoExcluir.setEnabled(false);
        janela.add(botaoExcluir);

        JLabel labelFiltro = new JLabel("Filtrar (Local):");
        labelFiltro.setBounds(20, 140, 120, 25);
        janela.add(labelFiltro);

        JTextField campoFiltro = new JTextField();
        campoFiltro.setBounds(140, 140, 160, 25);
        janela.add(campoFiltro);

        JButton botaoFiltrar = new JButton("FILTRAR");
        botaoFiltrar.setBounds(320, 140, 90, 25);
        janela.add(botaoFiltrar);

        JButton botaoGerarArquivo = new JButton("GERAR ARQ");
        botaoGerarArquivo.setBounds(420, 140, 90, 25);
        janela.add(botaoGerarArquivo);

        JButton botaoCancelar = new JButton("CANCELAR");
        botaoCancelar.setBounds(520, 140, 90, 25);
        janela.add(botaoCancelar);
        //Tabela 
        JTable tabela = new JTable();
        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBounds(20, 180, 600, 300);
        janela.add(scroll);
        
        PDiario pDiario = new PDiario();
        atualizarTabela(tabela, pDiario.listar(""));
        final int[] idSelecionado = {-1};

        tabela.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int linha = tabela.getSelectedRow();
                idSelecionado[0] = Integer.parseInt(tabela.getValueAt(linha, 0).toString());
                campoLocal.setText(tabela.getValueAt(linha, 1).toString());
                campoDisciplinas.setText(tabela.getValueAt(linha, 2).toString());
                campoQtdAlunos.setText(tabela.getValueAt(linha, 3).toString());
                campoFkProfessor.setText(tabela.getValueAt(linha, 4).toString());
                campoFkAluno.setText(tabela.getValueAt(linha, 5).toString());
                botaoAlterar.setEnabled(true);
                botaoExcluir.setEnabled(true);
            }
        });
        botaoSalvar.addActionListener(e -> {
            if (validarCampos(campoLocal, campoDisciplinas, campoQtdAlunos, campoFkProfessor, campoFkAluno)) {
                EDiario d = new EDiario();
                //abreviar para deixar o código menor para a leitura
                d.setDiarios_local(campoLocal.getText());
                d.setDiarios_disciplinas(campoDisciplinas.getText());
                d.setQtd_alunos(Integer.parseInt(campoQtdAlunos.getText()));
                d.setFk_diarios_professores_(Integer.parseInt(campoFkProfessor.getText()));
                d.setFk_diarios_alunos_(Integer.parseInt(campoFkAluno.getText()));
                String resultado = pDiario.inserir(d);
                JOptionPane.showMessageDialog(null, resultado);
                atualizarTabela(tabela, pDiario.listar(""));
                limparCampos(campoLocal, campoDisciplinas, campoQtdAlunos, campoFkProfessor, campoFkAluno);
                botaoAlterar.setEnabled(false);
                botaoExcluir.setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.");
            }
        });
        botaoAlterar.addActionListener(e -> {
            if (idSelecionado[0] != -1 && validarCampos(campoLocal, campoDisciplinas, campoQtdAlunos, campoFkProfessor, campoFkAluno)) {
                EDiario d = new EDiario();
                d.setDiarios_id(idSelecionado[0]);
                d.setDiarios_local(campoLocal.getText());
                d.setDiarios_disciplinas(campoDisciplinas.getText());
                d.setQtd_alunos(Integer.parseInt(campoQtdAlunos.getText()));
                d.setFk_diarios_professores_(Integer.parseInt(campoFkProfessor.getText()));
                d.setFk_diarios_alunos_(Integer.parseInt(campoFkAluno.getText()));
                String resultado = pDiario.alterar(d);
                JOptionPane.showMessageDialog(null, resultado);
                atualizarTabela(tabela, pDiario.listar(""));
                limparCampos(campoLocal, campoDisciplinas, campoQtdAlunos, campoFkProfessor, campoFkAluno);
                idSelecionado[0] = -1; // Resetar seleção
                botaoAlterar.setEnabled(false);
                botaoExcluir.setEnabled(false);
            } else if (idSelecionado[0] == -1) {
                JOptionPane.showMessageDialog(null, "Selecione um diário para alterar.");
            } else {
                JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.");
            }
        });
        botaoExcluir.addActionListener(e -> {
            if (idSelecionado[0] != -1) {
                int confirmacao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir este diário?", "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);
                if (confirmacao == JOptionPane.YES_OPTION) {
                    String resultado = pDiario.deletar(idSelecionado[0]);
                    JOptionPane.showMessageDialog(null, resultado);
                    atualizarTabela(tabela, pDiario.listar(""));
                    limparCampos(campoLocal, campoDisciplinas, campoQtdAlunos, campoFkProfessor, campoFkAluno);
                    idSelecionado[0] = -1; // Resetar seleção
                    botaoAlterar.setEnabled(false);
                    botaoExcluir.setEnabled(false);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Selecione um diário para excluir.");
            }
        });

        botaoFiltrar.addActionListener(e -> {
            atualizarTabela(tabela, pDiario.listar(campoFiltro.getText()));
        });

        botaoCancelar.addActionListener(e -> {
            // Limpa os campos e desabilita botões
            limparCampos(campoLocal, campoDisciplinas, campoQtdAlunos, campoFkProfessor, campoFkAluno);
            campoFiltro.setText("");
            idSelecionado[0] = -1;
            botaoAlterar.setEnabled(false);
            botaoExcluir.setEnabled(false);
            atualizarTabela(tabela, pDiario.listar("")); // Recarrega a tabela completa
        });
        botaoGerarArquivo.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Função de gerar arquivo ainda não implementada para Diário.");
        });

        janela.setVisible(true);
     }
    
}

