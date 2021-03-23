package view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.basic.BasicPanelUI;
import javax.swing.plaf.basic.BasicSplitPaneDivider;
import javax.swing.DropMode;
import javax.swing.ScrollPaneConstants;
import javax.swing.JSplitPane;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.border.BevelBorder;
import javax.swing.SwingConstants;
import javax.swing.TransferHandler;
import javax.swing.UIManager;

import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;

public class Main extends JFrame {

	private JPanel contentPane;
	static final double RATIO = 0.7;
	private JTextArea txaEditor;
	private JTextArea txaMenssagens;
	private JPanel pnlGeral;
	private JPanel pnlFerramentas;
	private JPanel pnlEditor;
	private JPanel pnlMenssagens;
	private JPanel pnlStatus;
	private JLabel msgStatus;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 150, 1000, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		this.setMinimumSize(new Dimension(900, 600));
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panel_5 = new JPanel();
		contentPane.add(panel_5);
		panel_5.setLayout(new GridLayout(1, 1, 0, 0));

		pnlGeral = new JPanel();
		panel_5.add(pnlGeral);
		GridBagLayout gbl_pnlGeral = new GridBagLayout();
		gbl_pnlGeral.columnWidths = new int[] { 190, 500 };
		gbl_pnlGeral.rowHeights = new int[] { 500, 500 };
		gbl_pnlGeral.columnWeights = new double[] { 0.0, 1.0 };
		gbl_pnlGeral.rowWeights = new double[] { 0.0, 0.0, 0.0 };
		pnlGeral.setLayout(gbl_pnlGeral);

		pnlFerramentas = new JPanel();
		setBarraFerramentas();

		pnlEditor = new JPanel();
		setEditor();

		pnlMenssagens = new JPanel();
		setAreaMenssagens();

		pnlStatus = new JPanel();
		setBarraStatus();

		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, true, pnlEditor, pnlMenssagens);
		GridBagConstraints gbc_splitPane = new GridBagConstraints();
		gbc_splitPane.gridheight = 2;
		gbc_splitPane.weighty = 100.0;
		gbc_splitPane.weightx = 100.0;
		gbc_splitPane.insets = new Insets(0, 0, 5, 0);
		gbc_splitPane.fill = GridBagConstraints.BOTH;
		gbc_splitPane.gridx = 1;
		gbc_splitPane.gridy = 0;
		pnlGeral.add(splitPane, gbc_splitPane);
		splitPane.setDividerLocation(550);
	}

	private void novo() {
		txaEditor.setText("");
		msgStatus.setText("");
	}

	private void salvar() {
		JFileChooser fileChooser = new JFileChooser();
		File fi = new File(msgStatus.getText());
		if (fi.exists()) {
			gravarArquivo(fi, txaEditor.getText());
		} else {
			if (fileChooser.showSaveDialog(getContentPane()) == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
				File f = new File(file.getPath());
				if (!file.exists()) {
					if (!file.getPath().endsWith(".txt")) {
						f = new File(file.getPath() + ".txt");
					}
					gravarArquivo(f, txaEditor.getText());
				}
				fi = f;
			}
		}
		msgStatus.setText(fi.getPath());
		txaMenssagens.setText("");
	}

	private void equipe() {
		txaMenssagens.setText("Equipe:  Renato Muller Reinhold");
	}

	private void abrir() {
		JFileChooser file = new JFileChooser();
		file.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int i = file.showSaveDialog(null);
		File arquivo = file.getSelectedFile();
		if (i != 1 && arquivo.getName().endsWith(".txt")) {
			txaEditor.setText("");
			txaMenssagens.setText("");
			msgStatus.setText(arquivo.getPath());
			List<String> list = new ArrayList<String>();
			try {
				BufferedReader reader = Files.newBufferedReader(arquivo.toPath(), StandardCharsets.ISO_8859_1);
				while(reader.ready()) {
					list.add(reader.readLine());
				}
				for (String line : list) {
					txaEditor.append(line + "\n");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}
	
	private void copiar() {
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		TransferHandler transferHandler = txaEditor.getTransferHandler();
		transferHandler.exportToClipboard(txaEditor, clipboard, TransferHandler.COPY);
	}

	private void colar() {
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		TransferHandler transferHandler = txaEditor.getTransferHandler();
		transferHandler.importData(txaEditor, clipboard.getContents(null));
	}
	
	private KeyStroke getKeyStroke(int key, int inputEvent) {
		return KeyStroke.getKeyStroke(key, inputEvent);
	}

	private void setShortCut(String name, Action action, int key, int inputEvent) {
		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(getKeyStroke(key, inputEvent), name);
		getRootPane().getActionMap().put(name, action);
	}

	private static void gravarArquivo(File f, String str) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(f));
			bw.write(str);
			bw.close();
		} catch (IOException e) {
			System.out.println("Erro ao gravar objeto.");
		}
	}
	
	private void compilar() {
		//TODO implentar compilacao de programas
		txaMenssagens.setText("compilação de programas ainda não foi implementada.");
	}

	private void setBarraStatus() {
		GridBagConstraints gbc_pnlStatus = new GridBagConstraints();
		gbc_pnlStatus.gridwidth = 2;
		gbc_pnlStatus.weightx = 100.0;
		gbc_pnlStatus.anchor = GridBagConstraints.NORTH;
		gbc_pnlStatus.fill = GridBagConstraints.HORIZONTAL;
		gbc_pnlStatus.gridx = 0;
		gbc_pnlStatus.gridy = 2;
		pnlGeral.add(pnlStatus, gbc_pnlStatus);
		GridBagLayout gbl_pnlStatus = new GridBagLayout();
		gbl_pnlStatus.columnWidths = new int[] { 900 };
		gbl_pnlStatus.rowHeights = new int[] { 25 };
		gbl_pnlStatus.columnWeights = new double[] { 0.0 };
		gbl_pnlStatus.rowWeights = new double[] { 0.0 };
		pnlStatus.setLayout(gbl_pnlStatus);

		msgStatus = new JLabel("pasta\\nome do arquivo");
		msgStatus.setFont(new Font("Arial", Font.BOLD, 12));
		GridBagConstraints gbc_msgStatus = new GridBagConstraints();
		gbc_msgStatus.weighty = 100.0;
		gbc_msgStatus.weightx = 100.0;
		gbc_msgStatus.fill = GridBagConstraints.BOTH;
		gbc_msgStatus.gridx = 0;
		gbc_msgStatus.gridy = 0;
		pnlStatus.add(msgStatus, gbc_msgStatus);
	}

	private void setAreaMenssagens() {
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.gridheight = 0;
		gbc_panel_2.insets = new Insets(0, 0, 5, 5);
		gbc_panel_2.weighty = 100.0;
		gbc_panel_2.weightx = 100.0;
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 1;
		GridBagLayout gbl_pnlMenssagens = new GridBagLayout();
		gbl_pnlMenssagens.columnWidths = new int[] { 0, 0 };
		gbl_pnlMenssagens.rowHeights = new int[] { 0, 0 };
		gbl_pnlMenssagens.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_pnlMenssagens.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		pnlMenssagens.setLayout(gbl_pnlMenssagens);

		txaMenssagens = new JTextArea();
		txaMenssagens.setEditable(false);
		txaMenssagens.setLineWrap(true);
		JScrollPane scrollPane2 = new JScrollPane(txaMenssagens);
		scrollPane2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		GridBagConstraints gbc_scrollPane2 = new GridBagConstraints();
		gbc_scrollPane2.weighty = 100.0;
		gbc_scrollPane2.weightx = 100.0;
		gbc_scrollPane2.fill = GridBagConstraints.BOTH;
		gbc_scrollPane2.gridx = 0;
		gbc_scrollPane2.gridy = 0;
		pnlMenssagens.add(scrollPane2, gbc_scrollPane2);
	}

	private void setEditor() {
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.weightx = 100.0;
		gbc_panel_1.weighty = 100.0;
		gbc_panel_1.anchor = GridBagConstraints.WEST;
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 0;
		GridBagLayout gbl_pnlEditor = new GridBagLayout();
		gbl_pnlEditor.columnWidths = new int[] { 0, 0 };
		gbl_pnlEditor.rowHeights = new int[] { 100, 0 };
		gbl_pnlEditor.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_pnlEditor.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		pnlEditor.setLayout(gbl_pnlEditor);

		txaEditor = new JTextArea();
		txaEditor.setBorder(new NumberedBorder());
		JScrollPane scrollPane = new JScrollPane(txaEditor);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.weighty = 100.0;
		gbc_scrollPane.weightx = 100.0;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		pnlEditor.add(scrollPane, gbc_scrollPane);
	}

	private void setBarraFerramentas() {
		GridBagConstraints gbc_pnlFerramentas = new GridBagConstraints();
		gbc_pnlFerramentas.anchor = GridBagConstraints.NORTH;
		gbc_pnlFerramentas.fill = GridBagConstraints.VERTICAL;
		gbc_pnlFerramentas.gridx = 0;
		gbc_pnlFerramentas.gridy = 0;
		pnlGeral.add(pnlFerramentas, gbc_pnlFerramentas);
		GridBagLayout gbl_pnlFerramentas = new GridBagLayout();
		gbl_pnlFerramentas.columnWidths = new int[] { 190, 10 };
		gbl_pnlFerramentas.rowHeights = new int[] { 62, 62, 62, 62, 62, 62, 62, 62 };
		gbl_pnlFerramentas.columnWeights = new double[] { 0.0 };
		gbl_pnlFerramentas.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
		pnlFerramentas.setLayout(gbl_pnlFerramentas);

		JButton btnNovo = new JButton("novo [ctrl-n]");
		btnNovo.setMnemonic(KeyEvent.VK_N);
		Action newAction = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				novo();
			}
		};
		btnNovo.addActionListener(newAction);
		setShortCut("newAction", newAction, KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK);

		btnNovo.setIcon(new ImageIcon(
				"C:\\Users\\renat\\eclipse-workspace\\TrabalhoCompiladores\\TrabalhoCompiladore\\resource\\img\\new-file (1).png"));
		btnNovo.setFont(new Font("Arial", Font.BOLD, 12));
		GridBagConstraints gbc_btnNovo = new GridBagConstraints();
		gbc_btnNovo.fill = GridBagConstraints.BOTH;
		gbc_btnNovo.gridx = 0;
		gbc_btnNovo.gridy = 0;
		pnlFerramentas.add(btnNovo, gbc_btnNovo);

		JButton btnAbrir = new JButton("abrir [ctrl-o]");
		Action abrirAction = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				abrir();
			}
		};
		btnAbrir.addActionListener(abrirAction);
		setShortCut("abrirAction", abrirAction, KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK);
		btnAbrir.setIcon(new ImageIcon(
				"C:\\Users\\renat\\eclipse-workspace\\TrabalhoCompiladores\\TrabalhoCompiladore\\resource\\img\\open.png"));
		btnAbrir.setFont(new Font("Arial", Font.BOLD, 12));
		GridBagConstraints gbc_btnAbrir = new GridBagConstraints();
		gbc_btnAbrir.fill = GridBagConstraints.BOTH;
		gbc_btnAbrir.gridx = 0;
		gbc_btnAbrir.gridy = 1;
		pnlFerramentas.add(btnAbrir, gbc_btnAbrir);

		JButton btnSalvar = new JButton("salvar [ctrl-s]");
		Action salvarAction = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				salvar();
			}
		};
		btnSalvar.addActionListener(salvarAction);
		setShortCut("salvarAction", salvarAction, KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK);
		btnSalvar.setIcon(new ImageIcon(
				"C:\\Users\\renat\\eclipse-workspace\\TrabalhoCompiladores\\TrabalhoCompiladore\\resource\\img\\save.png"));
		btnSalvar.setFont(new Font("Arial", Font.BOLD, 12));
		GridBagConstraints gbc_btnSalvar = new GridBagConstraints();
		gbc_btnSalvar.fill = GridBagConstraints.BOTH;
		gbc_btnSalvar.gridx = 0;
		gbc_btnSalvar.gridy = 2;
		pnlFerramentas.add(btnSalvar, gbc_btnSalvar);

		JButton btnCopiar = new JButton("copiar [ctrl-c]");
		Action copiarAction = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				colar();
			}
		};
		btnCopiar.addActionListener(copiarAction);
		setShortCut("copiarAction", copiarAction, KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK);
		btnCopiar.setIcon(new ImageIcon(
				"C:\\Users\\renat\\eclipse-workspace\\TrabalhoCompiladores\\TrabalhoCompiladore\\resource\\img\\images.png"));
		btnCopiar.setFont(new Font("Arial", Font.BOLD, 12));
		GridBagConstraints gbc_btnCopiar = new GridBagConstraints();
		gbc_btnCopiar.fill = GridBagConstraints.BOTH;
		gbc_btnCopiar.gridx = 0;
		gbc_btnCopiar.gridy = 3;
		pnlFerramentas.add(btnCopiar, gbc_btnCopiar);

		JButton btnColar = new JButton("colar [ctrl-v]");
		Action colarAction = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				colar();
			}
		};
		btnColar.addActionListener(colarAction);
		setShortCut("colarAction", colarAction, KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK);
		btnColar.setIcon(new ImageIcon(
				"C:\\Users\\renat\\eclipse-workspace\\TrabalhoCompiladores\\TrabalhoCompiladore\\resource\\img\\colar.png"));
		btnColar.setFont(new Font("Arial", Font.BOLD, 12));
		GridBagConstraints gbc_btnColar = new GridBagConstraints();
		gbc_btnColar.fill = GridBagConstraints.BOTH;
		gbc_btnColar.gridx = 0;
		gbc_btnColar.gridy = 4;
		pnlFerramentas.add(btnColar, gbc_btnColar);

		JButton btnRecortar = new JButton("recortar [ctrl-x]");
		Action recortarAction = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				copiar();
				txaEditor.replaceSelection("");
			}
		};
		btnRecortar.addActionListener(recortarAction);
		setShortCut("recortarAction", recortarAction, KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK);
		btnRecortar.setIcon(new ImageIcon(
				"C:\\Users\\renat\\eclipse-workspace\\TrabalhoCompiladores\\TrabalhoCompiladore\\resource\\img\\cut.png"));
		btnRecortar.setFont(new Font("Arial", Font.BOLD, 12));
		GridBagConstraints gbc_btnRecortar = new GridBagConstraints();
		gbc_btnRecortar.fill = GridBagConstraints.BOTH;
		gbc_btnRecortar.gridx = 0;
		gbc_btnRecortar.gridy = 5;
		pnlFerramentas.add(btnRecortar, gbc_btnRecortar);

		JButton btnCompilar = new JButton("compilar [F7]");
		Action compilarAction = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				compilar();
			}
		};
		btnCompilar.addActionListener(compilarAction);
		setShortCut("compilarAction", compilarAction, KeyEvent.VK_F7, 0);
		btnCompilar.setIcon(new ImageIcon(
				"C:\\Users\\renat\\eclipse-workspace\\TrabalhoCompiladores\\TrabalhoCompiladore\\resource\\img\\play.png"));
		btnCompilar.setFont(new Font("Arial", Font.BOLD, 12));
		GridBagConstraints gbc_btnCompilar = new GridBagConstraints();
		gbc_btnCompilar.fill = GridBagConstraints.BOTH;
		gbc_btnCompilar.gridx = 0;
		gbc_btnCompilar.gridy = 6;
		pnlFerramentas.add(btnCompilar, gbc_btnCompilar);

		JButton btnEquipe = new JButton("equipe [F1]");
		btnEquipe.setMnemonic(KeyEvent.VK_F1);
		Action equipeAction = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				equipe();
			}
		};
		btnEquipe.addActionListener(equipeAction);
		setShortCut("equipeAction", equipeAction, KeyEvent.VK_F1, 0);
		btnEquipe.setIcon(new ImageIcon(
				"C:\\Users\\renat\\eclipse-workspace\\TrabalhoCompiladores\\TrabalhoCompiladore\\resource\\img\\team.png"));
		btnEquipe.setFont(new Font("Arial", Font.BOLD, 12));
		GridBagConstraints gbc_btnEquipe = new GridBagConstraints();
		gbc_btnEquipe.fill = GridBagConstraints.BOTH;
		gbc_btnEquipe.gridx = 0;
		gbc_btnEquipe.gridy = 7;
		pnlFerramentas.add(btnEquipe, gbc_btnEquipe);
	}

}
