
package Entidades;

/**
 *
 * @author Kauã Luiz
 */
import Funcionalidades.EMatricula;
import Funcionalidades.PMatricula;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.*;

public class TelaMatricula {
    public static void montarTelaMatricula() throws IOException, SQLException {

    JFrame janela = new JFrame("Gerenciar Matrícula");
    janela.setBounds(200, 150, 650, 550);
    janela.setLayout(null);

    // Labels e campos
    JLabel labelDataInicio = new JLabel("Data Início:");
    labelDataInicio.setBounds(20, 20, 80, 25);
    janela.add(labelDataInicio);

    JTextField campoDataInicio = new JTextField();
    campoDataInicio.setBounds(100, 20, 200, 25);
    janela.add(campoDataInicio);

    JLabel labelDataFim = new JLabel("Data Fim:");
    labelDataFim.setBounds(20, 60, 80, 25);
    janela.add(labelDataFim);

    JTextField campoDataFim = new JTextField();
    campoDataFim.setBounds(100, 60, 200, 25);
    janela.add(campoDataFim);

    JLabel labelQtdTempo = new JLabel("Qtd Tempo:");
    labelQtdTempo.setBounds(20, 100, 80, 25);
    janela.add(labelQtdTempo);

    JTextField campoQtdTempo = new JTextField();
    campoQtdTempo.setBounds(100, 100, 200, 25);
    janela.add(campoQtdTempo);

    JLabel labelFkInstituicao = new JLabel("ID Instituição:");
    labelFkInstituicao.setBounds(320, 20, 100, 25);
    janela.add(labelFkInstituicao);

    JTextField campoFkInstituicao = new JTextField();
    campoFkInstituicao.setBounds(420, 20, 200, 25);
    janela.add(campoFkInstituicao);

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

    JLabel labelFiltro = new JLabel("Filtrar (ID Aluno):");
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

    // Tabela
    JTable tabela = new JTable();
    JScrollPane scroll = new JScrollPane(tabela);
    scroll.setBounds(20, 180, 600, 300);
    janela.add(scroll);

    PMatricula pMatricula = new PMatricula();
    atualizarTabela(tabela, pMatricula.consultarMatricula(""));

    final int[] idSelecionado = {-1};

    tabela.addMouseListener(new MouseAdapter() {
        public void mouseClicked(MouseEvent e) {
            int linha = tabela.getSelectedRow();
            idSelecionado[0] = Integer.parseInt(tabela.getValueAt(linha, 0).toString());
            campoDataInicio.setText(tabela.getValueAt(linha, 1).toString());
            campoDataFim.setText(tabela.getValueAt(linha, 2).toString());
            campoQtdTempo.setText(tabela.getValueAt(linha, 3).toString());
            campoFkInstituicao.setText(tabela.getValueAt(linha, 4).toString());
            campoFkAluno.setText(tabela.getValueAt(linha, 5).toString());
            botaoAlterar.setEnabled(true);
            botaoExcluir.setEnabled(true);
        }
    });

    botaoSalvar.addActionListener(e -> {
        if (validarCampos(campoDataInicio, campoDataFim, campoQtdTempo, campoFkInstituicao, campoFkAluno)) {
            EMatricula m = new EMatricula();
            m.setData_inicio(campoDataInicio.getText());
            m.setData_fim(campoDataFim.getText());
            m.setQtd_tempo(Integer.parseInt(campoQtdTempo.getText()));
            m.setFk_instituicao_id(Integer.parseInt(campoFkInstituicao.getText()));
            m.setFk_aluno_id(Integer.parseInt(campoFkAluno.getText()));
            String resultado = pMatricula.salvarMatricula(m);
            JOptionPane.showMessageDialog(null, resultado);
            atualizarTabela(tabela, pMatricula.consultarMatricula(""));
        }
    });

    botaoAlterar.addActionListener(e -> {
        if (idSelecionado[0] != -1 && validarCampos(campoDataInicio, campoDataFim, campoQtdTempo, campoFkInstituicao, campoFkAluno)) {
            EMatricula m = new EMatricula();
            m.setMatriculas_id(idSelecionado[0]);
            m.setData_inicio(campoDataInicio.getText());
            m.setData_fim(campoDataFim.getText());
            m.setQtd_tempo(Integer.parseInt(campoQtdTempo.getText()));
            m.setFk_instituicao_id(Integer.parseInt(campoFkInstituicao.getText()));
            m.setFk_aluno_id(Integer.parseInt(campoFkAluno.getText()));
            String resultado = pMatricula.alterarMatricula(m);
            JOptionPane.showMessageDialog(null, resultado);
            atualizarTabela(tabela, pMatricula.consultarMatricula(""));
        }
    });

    botaoExcluir.addActionListener(e -> {
        if (idSelecionado[0] != -1) {
            String resultado = pMatricula.excluirMatricula(idSelecionado[0]);
            JOptionPane.showMessageDialog(null, resultado);
            atualizarTabela(tabela, pMatricula.consultarMatricula(""));
        }
    });

    botaoFiltrar.addActionListener(e -> {
        atualizarTabela(tabela, pMatricula.consultarMatricula(campoFiltro.getText()));
    });

    botaoCancelar.addActionListener(e -> {
        try {
            montarTelaMatricula();
        } catch (IOException | SQLException ex) {
            ex.printStackTrace();
        }
        janela.dispose();
    });

    botaoGerarArquivo.addActionListener(e -> {
        JOptionPane.showMessageDialog(null, "Função de gerar arquivo ainda não implementada.");
    });

    janela.setVisible(true);
}

private static boolean validarCampos(JTextField... campos) {
    for (JTextField campo : campos) {
        if (campo.getText().trim().isEmpty()) {
            return false;
        }
    }
    return true;
      }   
}
