package view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicPanelUI;
import javax.swing.plaf.basic.BasicSplitPaneDivider;
import javax.swing.DropMode;
import javax.swing.ScrollPaneConstants;
import javax.swing.JSplitPane;
import javax.swing.ImageIcon;

public class Main extends JFrame {

	private JPanel contentPane;
	static final double RATIO = 0.7;

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

		JPanel panel_3 = new JPanel();
		panel_5.add(panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[] { 190, 500 };
		gbl_panel_3.rowHeights = new int[] { 500, 500 };
		gbl_panel_3.columnWeights = new double[] { 0.0, 1.0 };
		gbl_panel_3.rowWeights = new double[] { 0.0, 0.0, 0.0 };
		panel_3.setLayout(gbl_panel_3);

		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridheight = 2;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.anchor = GridBagConstraints.NORTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		panel_3.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 190, 0 };
		gbl_panel.rowHeights = new int[] { 55, 55, 55, 55, 55, 55, 55, 55 };
		gbl_panel.columnWeights = new double[] { 0.0 };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
		panel.setLayout(gbl_panel);

		JButton btnNovo = new JButton("novo [ctril-n]");
		btnNovo.setFont(new Font("Arial", Font.BOLD, 12));
		GridBagConstraints gbc_btnNovo = new GridBagConstraints();
		gbc_btnNovo.anchor = GridBagConstraints.NORTH;
		gbc_btnNovo.fill = GridBagConstraints.BOTH;
		gbc_btnNovo.insets = new Insets(0, 0, 5, 0);
		gbc_btnNovo.gridx = 0;
		gbc_btnNovo.gridy = 0;
		panel.add(btnNovo, gbc_btnNovo);

		JButton btnAbrir = new JButton("abrir [ctrl-o]");
		btnAbrir.setFont(new Font("Arial", Font.BOLD, 12));
		GridBagConstraints gbc_btnAbrir = new GridBagConstraints();
		gbc_btnAbrir.fill = GridBagConstraints.BOTH;
		gbc_btnAbrir.insets = new Insets(0, 0, 5, 0);
		gbc_btnAbrir.gridx = 0;
		gbc_btnAbrir.gridy = 1;
		panel.add(btnAbrir, gbc_btnAbrir);

		JButton btnSalvar = new JButton("salvar [ctrl-s]");
		btnSalvar.setFont(new Font("Arial", Font.BOLD, 12));
		GridBagConstraints gbc_btnSalvar = new GridBagConstraints();
		gbc_btnSalvar.fill = GridBagConstraints.BOTH;
		gbc_btnSalvar.insets = new Insets(0, 0, 5, 0);
		gbc_btnSalvar.gridx = 0;
		gbc_btnSalvar.gridy = 2;
		panel.add(btnSalvar, gbc_btnSalvar);

		JButton btnCopiar = new JButton("copiar [ctrl-c]");
		btnCopiar.setFont(new Font("Arial", Font.BOLD, 12));
		GridBagConstraints gbc_btnCopiar = new GridBagConstraints();
		gbc_btnCopiar.fill = GridBagConstraints.BOTH;
		gbc_btnCopiar.insets = new Insets(0, 0, 5, 0);
		gbc_btnCopiar.gridx = 0;
		gbc_btnCopiar.gridy = 3;
		panel.add(btnCopiar, gbc_btnCopiar);

		JButton btnColar = new JButton("colar [ctrl-v]");
		btnColar.setFont(new Font("Arial", Font.BOLD, 12));
		GridBagConstraints gbc_btnColar = new GridBagConstraints();
		gbc_btnColar.fill = GridBagConstraints.BOTH;
		gbc_btnColar.insets = new Insets(0, 0, 5, 0);
		gbc_btnColar.gridx = 0;
		gbc_btnColar.gridy = 4;
		panel.add(btnColar, gbc_btnColar);

		JButton btnRecortar = new JButton("recortar [ctrl-x]");
		btnRecortar.setFont(new Font("Arial", Font.BOLD, 12));
		GridBagConstraints gbc_btnRecortar = new GridBagConstraints();
		gbc_btnRecortar.fill = GridBagConstraints.BOTH;
		gbc_btnRecortar.insets = new Insets(0, 0, 5, 0);
		gbc_btnRecortar.gridx = 0;
		gbc_btnRecortar.gridy = 5;
		panel.add(btnRecortar, gbc_btnRecortar);

		JButton btnCompilar = new JButton("compilar [F7]");
		btnCompilar.setFont(new Font("Arial", Font.BOLD, 12));
		GridBagConstraints gbc_btnCompilar = new GridBagConstraints();
		gbc_btnCompilar.fill = GridBagConstraints.BOTH;
		gbc_btnCompilar.insets = new Insets(0, 0, 5, 0);
		gbc_btnCompilar.gridx = 0;
		gbc_btnCompilar.gridy = 6;
		panel.add(btnCompilar, gbc_btnCompilar);

		JButton btnEquipe = new JButton("equipe [F1]");
		btnEquipe.setFont(new Font("Arial", Font.BOLD, 12));
		GridBagConstraints gbc_btnEquipe = new GridBagConstraints();
		gbc_btnEquipe.fill = GridBagConstraints.BOTH;
		gbc_btnEquipe.gridx = 0;
		gbc_btnEquipe.gridy = 7;
		panel.add(btnEquipe, gbc_btnEquipe);

		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.weightx = 100.0;
		gbc_panel_1.weighty = 100.0;
		gbc_panel_1.anchor = GridBagConstraints.WEST;
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 0;
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] {0, 0};
		gbl_panel_1.rowHeights = new int[] {100, 0};
		gbl_panel_1.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);

		JTextArea txaEditor = new JTextArea();
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
		panel_1.add(scrollPane, gbc_scrollPane);

		JPanel panel_2 = new JPanel();
		
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.gridheight = 0;
		gbc_panel_2.insets = new Insets(0, 0, 5, 5);
		gbc_panel_2.weighty = 100.0;
		gbc_panel_2.weightx = 100.0;
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 1;
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[] {0, 0};
		gbl_panel_2.rowHeights = new int[] {0, 0};
		gbl_panel_2.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_panel_2.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panel_2.setLayout(gbl_panel_2);
		
		

		JTextArea txaMenssagens = new JTextArea();
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
		panel_2.add(scrollPane2, gbc_scrollPane2);

		JPanel panel_4 = new JPanel();
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.gridwidth = 2;
		gbc_panel_4.weightx = 100.0;
		gbc_panel_4.anchor = GridBagConstraints.NORTH;
		gbc_panel_4.fill = GridBagConstraints.BOTH;
		gbc_panel_4.gridx = 0;
		gbc_panel_4.gridy = 2;
		panel_3.add(panel_4, gbc_panel_4);
		GridBagLayout gbl_panel_4 = new GridBagLayout();
		gbl_panel_4.columnWidths = new int[] { 900 };
		gbl_panel_4.rowHeights = new int[] { 25 };
		gbl_panel_4.columnWeights = new double[] { 0.0 };
		gbl_panel_4.rowWeights = new double[] { 0.0 };
		panel_4.setLayout(gbl_panel_4);

		JLabel lblNewLabel_2 = new JLabel("New label");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.weighty = 100.0;
		gbc_lblNewLabel_2.weightx = 100.0;
		gbc_lblNewLabel_2.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 0;
		panel_4.add(lblNewLabel_2, gbc_lblNewLabel_2);

		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, true, panel_1, panel_2);
		GridBagConstraints gbc_splitPane = new GridBagConstraints();
		gbc_splitPane.gridheight = 2;
		gbc_splitPane.weighty = 100.0;
		gbc_splitPane.weightx = 100.0;
		gbc_splitPane.insets = new Insets(0, 0, 5, 0);
		gbc_splitPane.fill = GridBagConstraints.BOTH;
		gbc_splitPane.gridx = 1;
		gbc_splitPane.gridy = 0;
		panel_3.add(splitPane, gbc_splitPane);
		splitPane.setDividerLocation(550);
		
	}

}
