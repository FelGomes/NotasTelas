package Entidades;

import Funcionalidades.EInstituicao;
import Funcionalidades.PInstituicao;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.List;

public class TelaInstituicao {

    public static void montarTelaInstituicao() {

        JFrame frame = new JFrame("Instituição");
        frame.setBounds(450, 150, 850, 600);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(30, 20, 100, 20);
        frame.add(lblNome);

        JLabel lblEndereco = new JLabel("Endereço:");
        lblEndereco.setBounds(30, 60, 100, 20);
        frame.add(lblEndereco);

        JLabel lblCidade = new JLabel("Cidade:");
        lblCidade.setBounds(30, 100, 100, 20);
        frame.add(lblCidade);

        JLabel lblUF = new JLabel("UF:");
        lblUF.setBounds(30, 140, 100, 20);
        frame.add(lblUF);

        JLabel lblEscolaridade = new JLabel("Escolaridade:");
        lblEscolaridade.setBounds(30, 180, 100, 20);
        frame.add(lblEscolaridade);

        JLabel lblNivel = new JLabel("Nível:");
        lblNivel.setBounds(30, 220, 100, 20);
        frame.add(lblNivel);

        JTextField txtNome = new JTextField();
        txtNome.setBounds(150, 20, 250, 25);
        frame.add(txtNome);

        JTextField txtEndereco = new JTextField();
        txtEndereco.setBounds(150, 60, 250, 25);
        frame.add(txtEndereco);

        JTextField txtCidade = new JTextField();
        txtCidade.setBounds(150, 100, 250, 25);
        frame.add(txtCidade);

        String[] ufs = { "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO",
                          "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI",
                          "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" };
        JComboBox<String> comboUF = new JComboBox<>(ufs);
        comboUF.setBounds(150, 140, 150, 25);
        frame.add(comboUF);

        String[] escolaridades = { "Fundamental", "Médio", "Superior", "Pós-Graduação" };
        JComboBox<String> comboEscolaridade = new JComboBox<>(escolaridades);
        comboEscolaridade.setBounds(150, 180, 250, 25);
        frame.add(comboEscolaridade);

        String[] niveis = { "1", "2", "3", "4", "5" };
        JComboBox<String> comboNivel = new JComboBox<>(niveis);
        comboNivel.setBounds(150, 220, 150, 25);
        frame.add(comboNivel);

        String[] colunas = { "ID", "Nome", "Endereço", "Cidade", "UF", "Escolaridade", "Nível" };
        DefaultTableModel modelo = new DefaultTableModel(colunas, 0);
        JTable tabela = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBounds(30, 320, 770, 200);
        frame.add(scroll);

        JButton btnAdicionar = new JButton("Adicionar");
        btnAdicionar.setBounds(450, 20, 150, 30);
        frame.add(btnAdicionar);

        JButton btnAtualizar = new JButton("Atualizar");
        btnAtualizar.setBounds(450, 70, 150, 30);
        frame.add(btnAtualizar);

        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.setBounds(450, 120, 150, 30);
        frame.add(btnExcluir);

        JButton btnLimpar = new JButton("Limpar Campos");
        btnLimpar.setBounds(450, 170, 150, 30);
        frame.add(btnLimpar);

        JButton btnGerarArquivo = new JButton("Gerar Arquivo");
        btnGerarArquivo.setBounds(450, 220, 150, 30);
        frame.add(btnGerarArquivo);

        PInstituicao pInst = new PInstituicao();

        Runnable atualizarTabela = () -> {
            modelo.setRowCount(0);
            List<Object[]> lista = pInst.listarInstituicoes();
            for (Object[] obj : lista) {
                modelo.addRow(obj);
            }
        };

        Runnable limparCampos = () -> {
            txtNome.setText("");
            txtEndereco.setText("");
            txtCidade.setText("");
            comboUF.setSelectedIndex(0);
            comboEscolaridade.setSelectedIndex(0);
            comboNivel.setSelectedIndex(0);
            tabela.clearSelection();
        };

        Runnable validarCampos = () -> {
            if (txtNome.getText().trim().isEmpty() ||
                txtEndereco.getText().trim().isEmpty() ||
                txtCidade.getText().trim().isEmpty()) {
                throw new IllegalArgumentException("Preencha todos os campos obrigatórios.");
            }
        };

        btnAdicionar.addActionListener(e -> {
            try {
                validarCampos.run();
                EInstituicao inst = new EInstituicao();
                inst.setInst_nome(txtNome.getText());
                inst.setInst_endereco(txtEndereco.getText());
                inst.setInst_cidade(txtCidade.getText());
                inst.setInst_uf(comboUF.getSelectedItem().toString());
                inst.setInst_escolaridade(comboEscolaridade.getSelectedItem().toString());
                inst.setInst_nivel(Float.parseFloat(comboNivel.getSelectedItem().toString()));

                pInst.inserirInstituicao(inst);
                JOptionPane.showMessageDialog(frame, "Instituição adicionada com sucesso!");
                atualizarTabela.run();
                limparCampos.run();
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(frame, ex.getMessage());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Erro ao adicionar: " + ex.getMessage());
            }
        });

        btnAtualizar.addActionListener(e -> {
            int linha = tabela.getSelectedRow();
            if (linha >= 0) {
                try {
                    validarCampos.run();
                    int id = Integer.parseInt(tabela.getValueAt(linha, 0).toString());

                    EInstituicao inst = new EInstituicao();
                    inst.setInst_nome(txtNome.getText());
                    inst.setInst_endereco(txtEndereco.getText());
                    inst.setInst_cidade(txtCidade.getText());
                    inst.setInst_uf(comboUF.getSelectedItem().toString());
                    inst.setInst_escolaridade(comboEscolaridade.getSelectedItem().toString());
                    inst.setInst_nivel(Float.parseFloat(comboNivel.getSelectedItem().toString()));

                    pInst.atualizarInstituicao(id, inst);
                    JOptionPane.showMessageDialog(frame, "Instituição atualizada com sucesso!");
                    atualizarTabela.run();
                    limparCampos.run();
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Erro ao atualizar: " + ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Selecione uma linha na tabela.");
            }
        });

        btnExcluir.addActionListener(e -> {
            int linha = tabela.getSelectedRow();
            if (linha >= 0) {
                try {
                    int id = Integer.parseInt(tabela.getValueAt(linha, 0).toString());
                    pInst.excluirInstituicao(id);
                    JOptionPane.showMessageDialog(frame, "Instituição excluída com sucesso!");
                    atualizarTabela.run();
                    limparCampos.run();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Erro ao excluir: " + ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Selecione uma linha na tabela.");
            }
        });

        btnLimpar.addActionListener(e -> limparCampos.run());

        btnGerarArquivo.addActionListener(e -> {
            try {
                pInst.gerarArquivoInstituicoes();
                JOptionPane.showMessageDialog(frame, "Arquivo Gerado");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Erro ao gerar arquivo: " + ex.getMessage());
            }
        });

        tabela.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int linha = tabela.getSelectedRow();
                if (linha >= 0) {
                    txtNome.setText(tabela.getValueAt(linha, 1).toString());
                    txtEndereco.setText(tabela.getValueAt(linha, 2).toString());
                    txtCidade.setText(tabela.getValueAt(linha, 3).toString());
                    comboUF.setSelectedItem(tabela.getValueAt(linha, 4).toString());
                    comboEscolaridade.setSelectedItem(tabela.getValueAt(linha, 5).toString());
                    comboNivel.setSelectedItem(tabela.getValueAt(linha, 6).toString());
                }
            }
        });

        atualizarTabela.run();
        frame.setVisible(true);
    }
}
