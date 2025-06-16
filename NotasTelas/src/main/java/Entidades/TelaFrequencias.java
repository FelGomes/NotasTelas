package Entidades;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import Funcionalidades.EFrequencias;
import Funcionalidades.PFrequencias;

import java.util.ArrayList;

public class TelaFrequencias {

    public static void montarTelaFrequencia() throws IOException {
        JFrame janela = new JFrame("FREQUÊNCIA");
        janela.setSize(700, 550);
        janela.setLayout(null);
        janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Evita fechar o app inteiro

        // Rótulos
        JLabel rotuloTotalAulas = new JLabel("Total de Aulas:");
        rotuloTotalAulas.setBounds(30, 20, 120, 20);
        janela.add(rotuloTotalAulas);

        JLabel rotuloAulasMinistradas = new JLabel("Aulas Ministradas:");
        rotuloAulasMinistradas.setBounds(30, 50, 150, 20);
        janela.add(rotuloAulasMinistradas);

        JLabel rotuloFaltas = new JLabel("Faltas:");
        rotuloFaltas.setBounds(30, 80, 120, 20);
        janela.add(rotuloFaltas);

        JLabel rotuloDisciplina = new JLabel("Disciplina:");
        rotuloDisciplina.setBounds(30, 110, 120, 20);
        janela.add(rotuloDisciplina);

        // Campos
        JTextField campoTotalAulas = new JTextField();
        campoTotalAulas.setBounds(180, 20, 200, 20);
        janela.add(campoTotalAulas);

        JTextField campoAulasMinistradas = new JTextField();
        campoAulasMinistradas.setBounds(180, 50, 200, 20);
        janela.add(campoAulasMinistradas);

        JTextField campoFaltas = new JTextField();
        campoFaltas.setBounds(180, 80, 200, 20);
        janela.add(campoFaltas);

        JTextField campoDisciplina = new JTextField();
        campoDisciplina.setBounds(180, 110, 200, 20);
        janela.add(campoDisciplina);

        // Botões
        JButton botaoSalvar = new JButton("SALVAR");
        botaoSalvar.setBounds(400, 20, 100, 25);
        janela.add(botaoSalvar);

        JButton botaoListar = new JButton("LISTAR");
        botaoListar.setBounds(400, 60, 100, 25);
        janela.add(botaoListar);

        JButton botaoLimpar = new JButton("LIMPAR");
        botaoLimpar.setBounds(400, 100, 100, 25);
        janela.add(botaoLimpar);

        // Tabela
        JTable tabela = new JTable();
        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBounds(30, 160, 620, 300);
        janela.add(scroll);

        PFrequencias pFrequencias = new PFrequencias();
        atualizarTabela(tabela, pFrequencias.listarFrequencias());

        botaoSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (validarCampos(campoTotalAulas, campoAulasMinistradas, campoFaltas, campoDisciplina)) {
                    EFrequencias freq = new EFrequencias();
                    freq.setTotal_aulas(Integer.parseInt(campoTotalAulas.getText()));
                    freq.setAulas_ministradas(Integer.parseInt(campoAulasMinistradas.getText()));
                    freq.setFrequencias_faltas(Integer.parseInt(campoFaltas.getText()));
                    freq.setFrequencias_disciplinas(campoDisciplina.getText());

                    String resultado = pFrequencias.incluirFrequencia(freq);
                    JOptionPane.showMessageDialog(null, resultado);
                    atualizarTabela(tabela, pFrequencias.listarFrequencias());
                    limparCampos(campoTotalAulas, campoAulasMinistradas, campoFaltas, campoDisciplina);
                } else {
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos corretamente.");
                }
            }
        });

        botaoListar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                atualizarTabela(tabela, pFrequencias.listarFrequencias());
            }
        });

        botaoLimpar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                limparCampos(campoTotalAulas, campoAulasMinistradas, campoFaltas, campoDisciplina);
            }
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

    private static void limparCampos(JTextField... campos) {
        for (JTextField campo : campos) {
            campo.setText("");
        }
    }

    private static void atualizarTabela(JTable tabela, ArrayList<EFrequencias> lista) {
        String[] colunas = {"ID", "Total Aulas", "Aulas Ministradas", "Faltas", "Disciplina"};
        String[][] dados = new String[lista.size()][5];

        for (int i = 0; i < lista.size(); i++) {
            EFrequencias f = lista.get(i);
            dados[i][0] = String.valueOf(f.getFrequencias_id());
            dados[i][1] = String.valueOf(f.getTotal_aulas());
            dados[i][2] = String.valueOf(f.getAulas_ministradas());
            dados[i][3] = String.valueOf(f.getFrequencias_faltas());
            dados[i][4] = f.getFrequencias_disciplinas();
        }
        tabela.setModel(new javax.swing.table.DefaultTableModel(dados, colunas));
    }
}