package Entidades;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.text.MaskFormatter;
import Funcionalidades.PUsuarios;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Usuarios {

    public static void montarTelaUsuario() throws IOException, SQLException {

        JFrame janela = new JFrame("INSERIR USUARIO");
        janela.setBounds(200, 150, 600, 550);
        janela.setLayout(null);

        JLabel nomeUsuario = new JLabel("NOME:");
        nomeUsuario.setBounds(5, 20, 60, 25);
        nomeUsuario.setHorizontalAlignment(JLabel.RIGHT);
        janela.add(nomeUsuario);

        JTextField campoUsuario = new JTextField();
        campoUsuario.setBounds(20, 45, 250, 25);
        janela.add(campoUsuario);

        JLabel nomeEndereco = new JLabel("ENDERECO:");
        nomeEndereco.setBounds(5, 80, 80, 25);
        nomeEndereco.setHorizontalAlignment(JLabel.RIGHT);
        janela.add(nomeEndereco);

        JTextField campoEndereco = new JTextField();
        campoEndereco.setBounds(20, 108, 250, 25);
        janela.add(campoEndereco);

        JLabel nomeCPF = new JLabel("CPF:");
        nomeCPF.setBounds(310, 20, 60, 25);
        nomeCPF.setHorizontalAlignment(JLabel.RIGHT);
        janela.add(nomeCPF);

        JTextField campoCPF = new JFormattedTextField(mascaraCPF("###.###.###-##"));
        campoCPF.setBounds(340, 45, 250, 25);
        janela.add(campoCPF);

        JLabel campoSexo = new JLabel("SEXO:");
        campoSexo.setBounds(315, 80, 60, 25);
        campoSexo.setHorizontalAlignment(JLabel.RIGHT);
        janela.add(campoSexo);

        JComboBox<String> ojComboBox = new JComboBox<>(new String[]{"-SELECIONE-", "M", "F"});
        ojComboBox.setBounds(338, 108, 120, 25);
        janela.add(ojComboBox);

        JLabel nomeData = new JLabel("DAT.NASC");
        nomeData.setBounds(20, 140, 60, 25);
        nomeData.setHorizontalAlignment(JLabel.RIGHT);
        janela.add(nomeData);

        JTextField campoData = new JFormattedTextField(mascaraData("##/##/####"));
        campoData.setBounds(20, 170, 100, 25);
        janela.add(campoData);

        JButton botaoSalvar = new JButton("SAVE");
        botaoSalvar.setBounds(350, 170, 80, 25);
        janela.add(botaoSalvar);

        JButton botaoCancelar = new JButton("CANCEL");
        botaoCancelar.setBounds(450, 170, 100, 25);
        janela.add(botaoCancelar);

        JButton botaoAlterar = new JButton("ALTER");
        botaoAlterar.setBounds(350, 205, 80, 25);
        botaoAlterar.setEnabled(false);
        janela.add(botaoAlterar);

        JButton botaoDeletar = new JButton("DEL");
        botaoDeletar.setBounds(450, 205, 100, 25);
        botaoDeletar.setEnabled(false);
        janela.add(botaoDeletar);

        JButton botaoFiltrar = new JButton("Filtrar");
        botaoFiltrar.setBounds(350, 240, 80, 25);
        janela.add(botaoFiltrar);

        JButton botaoArquivo = new JButton("GER.ARQ");
        botaoArquivo.setBounds(450, 240, 100, 25);
        janela.add(botaoArquivo);

        JLabel nomeFiltro = new JLabel("FILTRO:");
        nomeFiltro.setBounds(10, 205, 60, 25);
        nomeFiltro.setHorizontalAlignment(JLabel.RIGHT);
        janela.add(nomeFiltro);

        JTextField campoFiltro = new JTextField();
        campoFiltro.setBounds(20, 230, 250, 25);
        janela.add(campoFiltro);

        String[] colunas = {"ID", "Nome", "Sexo", "CPF", "Endereco", "Nascimento"};
        PUsuarios pUsuarios = new PUsuarios();

        JTable tabela = new JTable();
        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBounds(30, 270, 515, 220);
        janela.add(scroll);

        atualizarTabela(tabela, pUsuarios.listarUsuarios());

        botaoSalvar.addActionListener(e -> {
            if (validarCampos(campoUsuario, campoCPF, campoEndereco, campoData, ojComboBox)) {
                String resultado = pUsuarios.incluirUsuario(
                        campoUsuario.getText(),
                        ojComboBox.getSelectedItem().toString(),
                        campoCPF.getText(),
                        campoEndereco.getText(),
                        campoData.getText()
                );
                JOptionPane.showMessageDialog(null, resultado);
                try {
                    montarTelaUsuario();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (SQLException ex) {
                    Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
                }
                janela.dispose();
            }
        });

        final int[] idSelecionado = {-1};
        tabela.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                idSelecionado[0] = Integer.parseInt(tabela.getValueAt(tabela.getSelectedRow(), 0).toString());
                campoUsuario.setText(tabela.getValueAt(tabela.getSelectedRow(), 1).toString());
                ojComboBox.setSelectedItem(tabela.getValueAt(tabela.getSelectedRow(), 2).toString());
                campoCPF.setText(tabela.getValueAt(tabela.getSelectedRow(), 3).toString());
                campoEndereco.setText(tabela.getValueAt(tabela.getSelectedRow(), 4).toString());
                campoData.setText(tabela.getValueAt(tabela.getSelectedRow(), 5).toString());
                botaoAlterar.setEnabled(true);
                botaoDeletar.setEnabled(true);
            }
        });

        botaoAlterar.addActionListener(e -> {
            if (idSelecionado[0] != -1 && validarCampos(campoUsuario, campoCPF, campoEndereco, campoData, ojComboBox)) {
                String resultado = pUsuarios.alterarUsuario(
                        idSelecionado[0],
                        campoUsuario.getText(),
                        ojComboBox.getSelectedItem().toString(),
                        campoEndereco.getText(),
                        campoData.getText()
                );
                JOptionPane.showMessageDialog(null, resultado);
                try {
                    montarTelaUsuario();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (SQLException ex) {
                    Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
                }
                janela.dispose();
            }
        });

        botaoDeletar.addActionListener(e -> {
            if (idSelecionado[0] != -1) {
                String resultado = pUsuarios.excluirUsuario(idSelecionado[0]);
                JOptionPane.showMessageDialog(null, resultado);
                try {
                    montarTelaUsuario();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (SQLException ex) {
                    Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
                }
                janela.dispose();
            }
        });

        botaoFiltrar.addActionListener(e -> {
            try {
                atualizarTabela(tabela, pUsuarios.buscarUsuariosPorNome(campoFiltro.getText()));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        botaoCancelar.addActionListener(e -> {
            try {
                montarTelaUsuario();
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (SQLException ex) {
                Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
            }
            janela.dispose();
        });

        janela.setVisible(true);
    }

    private static boolean validarCampos(JTextField nome, JTextField cpf, JTextField end, JTextField data, JComboBox<String> sexo) {
        return !nome.getText().trim().isEmpty()
                && !cpf.getText().trim().isEmpty()
                && !end.getText().trim().isEmpty()
                && !data.getText().trim().isEmpty()
                && sexo.getSelectedIndex() != 0;
    }

    private static void atualizarTabela(JTable tabela, ArrayList<String[]> dados) {
        String[] colunas = {"ID", "Nome", "Sexo", "CPF", "Endereco", "Nascimento"};
        String[][] linhas = dados.toArray(new String[0][]);
        tabela.setModel(new javax.swing.table.DefaultTableModel(linhas, colunas));
    }

    public static MaskFormatter mascaraCPF(String mascara) {
        try {
            MaskFormatter formatter = new MaskFormatter(mascara);
            formatter.setPlaceholder("000.000.000-00");
            return formatter;
        } catch (ParseException e) {
            return null;
        }
    }

    public static MaskFormatter mascaraData(String mascara) {
        try {
            MaskFormatter formatter = new MaskFormatter(mascara);
            formatter.setPlaceholderCharacter(' ');
            return formatter;
        } catch (ParseException e) {
            return null;
        }
    }
}
