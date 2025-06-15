package Entidades;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.List;
import Funcionalidades.PNotas;
import java.io.IOException;

public class TelaNotas {

    public static void montarTelaNotas() {
        JFrame frame = new JFrame("Notas");
        frame.setBounds(400, 150, 850, 780);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        PNotas dao = new PNotas();

        JLabel lblUsuarios = new JLabel("Todos os Usuários:");
        lblUsuarios.setBounds(10, 10, 200, 25);
        frame.add(lblUsuarios);

        String[] colUsuarios = {"ID", "Nome", "CPF"};
        DefaultTableModel modeloUsuarios = new DefaultTableModel(colUsuarios, 0);
        JTable tabelaUsuarios = new JTable(modeloUsuarios);
        JScrollPane scrollUsuarios = new JScrollPane(tabelaUsuarios);
        scrollUsuarios.setBounds(10, 40, 780, 100);
        frame.add(scrollUsuarios);

        JLabel lblAlunos = new JLabel("Todos os Alunos:");
        lblAlunos.setBounds(10, 145, 200, 25);
        frame.add(lblAlunos);

        String[] colAlunos = {"ID", "Nome"};
        DefaultTableModel modeloAlunos = new DefaultTableModel(colAlunos, 0);
        JTable tabelaAlunos = new JTable(modeloAlunos);
        JScrollPane scrollAlunos = new JScrollPane(tabelaAlunos);
        scrollAlunos.setBounds(10, 170, 370, 100);
        frame.add(scrollAlunos);

        JLabel lblProfessores = new JLabel("Todos os Professores:");
        lblProfessores.setBounds(420, 145, 200, 25);
        frame.add(lblProfessores);

        String[] colProfs = {"ID", "Nome", "Disciplina"};
        DefaultTableModel modeloProfessores = new DefaultTableModel(colProfs, 0);
        JTable tabelaProfessores = new JTable(modeloProfessores);
        JScrollPane scrollProfessores = new JScrollPane(tabelaProfessores);
        scrollProfessores.setBounds(420, 170, 370, 100);
        frame.add(scrollProfessores);

        JLabel lblNotas = new JLabel("Notas Lançadas:");
        lblNotas.setBounds(10, 280, 200, 25);
        frame.add(lblNotas);

        String[] colNotas = {"ID Aluno", "Nome Aluno", "ID Professor", "Nome Professor",
                            "Nota 1", "Nota 2", "Nota 3", "Nota 4", "Média", "Disciplina"};
        DefaultTableModel modeloNotas = new DefaultTableModel(colNotas, 0);
        JTable tabelaNotas = new JTable(modeloNotas);
        JScrollPane scrollNotas = new JScrollPane(tabelaNotas);
        scrollNotas.setBounds(10, 310, 780, 180);
        frame.add(scrollNotas);

        int labelX = 10, labelW = 100, fieldX = 110, fieldW = 150, height = 25, gapY = 35;
        int startY = 500;

        JLabel lblIdAluno = new JLabel("ID Aluno:");
        lblIdAluno.setBounds(labelX, startY, labelW, height);
        frame.add(lblIdAluno);
        JTextField txtIdAluno = new JTextField();
        txtIdAluno.setBounds(fieldX, startY, fieldW, height);
        frame.add(txtIdAluno);

        JLabel lblIdProfessor = new JLabel("ID Professor:");
        lblIdProfessor.setBounds(labelX, startY + gapY, labelW, height);
        frame.add(lblIdProfessor);
        JTextField txtIdProfessor = new JTextField();
        txtIdProfessor.setBounds(fieldX, startY + gapY, fieldW, height);
        frame.add(txtIdProfessor);

        JLabel lblNota1 = new JLabel("Nota 1:");
        lblNota1.setBounds(labelX, startY + 2*gapY, labelW, height);
        frame.add(lblNota1);
        JTextField txtNota1 = new JTextField();
        txtNota1.setBounds(fieldX, startY + 2*gapY, fieldW, height);
        frame.add(txtNota1);

        JLabel lblNota2 = new JLabel("Nota 2:");
        lblNota2.setBounds(labelX, startY + 3*gapY, labelW, height);
        frame.add(lblNota2);
        JTextField txtNota2 = new JTextField();
        txtNota2.setBounds(fieldX, startY + 3*gapY, fieldW, height);
        frame.add(txtNota2);

        JLabel lblNota3 = new JLabel("Nota 3:");
        lblNota3.setBounds(labelX, startY + 4*gapY, labelW, height);
        frame.add(lblNota3);
        JTextField txtNota3 = new JTextField();
        txtNota3.setBounds(fieldX, startY + 4*gapY, fieldW, height);
        frame.add(txtNota3);

        JLabel lblNota4 = new JLabel("Nota 4:");
        lblNota4.setBounds(labelX, startY + 5*gapY, labelW, height);
        frame.add(lblNota4);
        JTextField txtNota4 = new JTextField();
        txtNota4.setBounds(fieldX, startY + 5*gapY, fieldW, height);
        frame.add(txtNota4);

        JLabel lblDisciplina = new JLabel("Disciplina:");
        lblDisciplina.setBounds(400, startY, labelW, height);
        frame.add(lblDisciplina);
        JTextField txtDisciplina = new JTextField();
        txtDisciplina.setBounds(500, startY, fieldW, height);
        frame.add(txtDisciplina);

        JButton btnInserir = new JButton("Inserir");
        btnInserir.setBounds(400, startY + gapY, 100, 30);
        frame.add(btnInserir);

        JButton btnAtualizar = new JButton("Atualizar");
        btnAtualizar.setBounds(520, startY + gapY, 100, 30);
        frame.add(btnAtualizar);

        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.setBounds(640, startY + gapY, 100, 30);
        frame.add(btnExcluir);

        JButton btnLimpar = new JButton("Limpar");
        btnLimpar.setBounds(520, startY + 2*gapY + 5, 100, 30);
        frame.add(btnLimpar);
        
        JButton btnGerArquivo = new JButton("Ger.Arq");
        btnGerArquivo.setBounds(520, startY + 2*gapY + 5 + 35, 100, 30);
        frame.add(btnGerArquivo);
        
        JLabel lblFiltroDisciplina = new JLabel("Filtrar Disciplina:");
        lblFiltroDisciplina.setBounds(400, startY + 2 * gapY + 5 + 35 + gapY, labelW, height);
        frame.add(lblFiltroDisciplina);

        JTextField campoFiltro = new JTextField();
        campoFiltro.setBounds(500, startY + 2 * gapY + 5 + 35 + gapY, fieldW, height);
        frame.add(campoFiltro);

        JButton btnFiltar = new JButton("Filtrar");
        btnFiltar.setBounds(710, startY + 2 * gapY + 5 + 35 + gapY, 120, 30);
        frame.add(btnFiltar);
        
        Runnable atualizarTabelas = () -> {
            modeloUsuarios.setRowCount(0);
            for (Object[] u : dao.listarUsuarios()) modeloUsuarios.addRow(u);

            modeloAlunos.setRowCount(0);
            for (Object[] a : dao.listarAlunos()) modeloAlunos.addRow(a);

            modeloProfessores.setRowCount(0);
            for (Object[] p : dao.listarProfessores()) modeloProfessores.addRow(p);

            modeloNotas.setRowCount(0);
            for (Object[] n : dao.listarNotas()) modeloNotas.addRow(n);
        };
        atualizarTabelas.run();

        tabelaNotas.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                int row = tabelaNotas.getSelectedRow();
                if (row != -1) {
                    txtIdAluno.setText(modeloNotas.getValueAt(row, 0).toString());
                    txtIdProfessor.setText(modeloNotas.getValueAt(row, 2).toString());
                    txtNota1.setText(modeloNotas.getValueAt(row, 4).toString());
                    txtNota2.setText(modeloNotas.getValueAt(row, 5).toString());
                    txtNota3.setText(modeloNotas.getValueAt(row, 6).toString());
                    txtNota4.setText(modeloNotas.getValueAt(row, 7).toString());
                    txtDisciplina.setText(modeloNotas.getValueAt(row, 9).toString());
                }
            }
        });

        btnInserir.addActionListener(e -> {
            try {
                int idAluno = Integer.parseInt(txtIdAluno.getText());
                int idProfessor = Integer.parseInt(txtIdProfessor.getText());
                double n1 = Double.parseDouble(txtNota1.getText());
                double n2 = Double.parseDouble(txtNota2.getText());
                double n3 = Double.parseDouble(txtNota3.getText());
                double n4 = Double.parseDouble(txtNota4.getText());
                String disciplina = txtDisciplina.getText();

                if (!dao.alunoExiste(idAluno)) {
                    JOptionPane.showMessageDialog(frame, "Aluno não encontrado.");
                    return;
                }
                if (!dao.professorExiste(idProfessor)) {
                    JOptionPane.showMessageDialog(frame, "Professor não encontrado.");
                    return;
                }

                dao.inserirNota(idAluno, idProfessor, n1, n2, n3, n4, disciplina);
                JOptionPane.showMessageDialog(frame, "Nota inserida com sucesso.");
                atualizarTabelas.run();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Preencha todos os campos corretamente.");
            }
        });

        btnAtualizar.addActionListener(e -> {
            try {
                int idAluno = Integer.parseInt(txtIdAluno.getText());
                int idProfessor = Integer.parseInt(txtIdProfessor.getText());
                double n1 = Double.parseDouble(txtNota1.getText());
                double n2 = Double.parseDouble(txtNota2.getText());
                double n3 = Double.parseDouble(txtNota3.getText());
                double n4 = Double.parseDouble(txtNota4.getText());
                String disciplina = txtDisciplina.getText();

                if (!dao.alunoExiste(idAluno)) {
                    JOptionPane.showMessageDialog(frame, "Aluno não encontrado.");
                    return;
                }
                if (!dao.professorExiste(idProfessor)) {
                    JOptionPane.showMessageDialog(frame, "Professor não encontrado.");
                    return;
                }

                dao.atualizarNota(idAluno, idProfessor, n1, n2, n3, n4, disciplina);
                JOptionPane.showMessageDialog(frame, "Nota atualizada com sucesso.");
                atualizarTabelas.run();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Preencha todos os campos corretamente.");
            }
        });
        
        btnFiltar.addActionListener(e -> {
            String textoFiltro = campoFiltro.getText().trim();
            if (textoFiltro.isEmpty()) {
                atualizarTabelas.run(); // Volta para a lista completa
                return;
            }
            try {
                List<Object[]> listaFiltrada = dao.buscarNotasPorDisciplina(textoFiltro);
                atualizarTabela(modeloNotas, listaFiltrada);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Erro ao filtrar notas por disciplina.");
            }
        });


        
        btnGerArquivo.addActionListener(e -> {
            try {
                PNotas pNotas = new PNotas(); // instância da classe de persistência
                pNotas.gerarArquivoNotas();
                JOptionPane.showMessageDialog(frame, "Arquivo 'notas.txt' gerado com sucesso!");
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Erro ao gerar o arquivo.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });


        btnExcluir.addActionListener(e -> {
            try {
                int idAluno = Integer.parseInt(txtIdAluno.getText());
                int idProfessor = Integer.parseInt(txtIdProfessor.getText());

                dao.excluirNota(idAluno, idProfessor);
                JOptionPane.showMessageDialog(frame, "Nota excluída com sucesso.");
                atualizarTabelas.run();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "ID inválido.");
            }
        });

        btnLimpar.addActionListener(e -> {
            txtIdAluno.setText("");
            txtIdProfessor.setText("");
            txtNota1.setText("");
            txtNota2.setText("");
            txtNota3.setText("");
            txtNota4.setText("");
            txtDisciplina.setText("");
        });

        frame.setVisible(true);
    }
    
    private static void atualizarTabela(DefaultTableModel modelo, List<Object[]> dados) {
        modelo.setRowCount(0); // Limpa o conteúdo atual da tabela
        for (Object[] linha : dados) {
            modelo.addRow(linha); // Adiciona cada nova linha vinda da lista
        }
}

    
    
}
