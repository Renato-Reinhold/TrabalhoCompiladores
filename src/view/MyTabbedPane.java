package view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.plaf.synth.Region;
import javax.swing.plaf.synth.SynthConstants;
import javax.swing.plaf.synth.SynthContext;
import javax.swing.plaf.synth.SynthLookAndFeel;
import javax.swing.plaf.synth.SynthStyle;

/**
 * 
 * 
 * @author Onyas D. Claudio
 */
public class MyTabbedPane extends JTabbedPane {


    /** Variável que verifica se o componentejá foi iniciado. */
    private boolean componenteIniciado = false;


    public MyTabbedPane() {
        super();
        addComponentListener(new ComponentAdapter() {
            @Override public void componentResized(ComponentEvent e) {
                if(!componenteIniciado) {
                    initTabWidth();
                    componenteIniciado = true;
                } // Fim do if
            } // Fim do método componentResized
        });
    } // Fim do construtor

    /**
     * Retorna o <code>Insets</code> da aba, os limites dela.
     *
     * @return um objeto <code>Insetx</code> com os limites da aba.
     */
    private Insets getTabInsets() {
        Insets insets = UIManager.getInsets("TabbedPane.tabInsets");
        if(insets != null) {
            return insets;
        } else {
            SynthStyle estilo = SynthLookAndFeel.getStyle(this,
                    Region.TABBED_PANE_TAB);
            SynthContext contexto = new SynthContext(this, Region.TABBED_PANE_TAB,
                    estilo, SynthConstants.ENABLED);
            return estilo.getInsets(contexto, null);
        } // Fim do if/else
    } // Fim do método getTabInsets

    /**
     * Retorna o <code>Insets</code> da área da aba, os limites dela.
     *
     * @return um objeto <code>Insetx</code> com os limites da área da aba.
     */
    private Insets getTabAreaInsets() {
        Insets insets = UIManager.getInsets("TabbedPane.tabAreaInsets");
        if(insets != null) {
            return insets;
        } else {
            SynthStyle estilo = SynthLookAndFeel.getStyle(this,
                    Region.TABBED_PANE_TAB_AREA);
            SynthContext contexto = new SynthContext(this,
                    Region.TABBED_PANE_TAB_AREA, estilo, SynthConstants.ENABLED);
            return estilo.getInsets(contexto, null);
        } // Fim do if/else
    } // Fim do método getTabAreaInsets

    /**
     * Inicializa a largura da aba.
     */
    private void initTabWidth() {
        int tabCount = getTabCount();
        if(tabCount == 0) return;
        Insets tabInsets = this.getTabInsets();
        Insets tabAreaInsets = this.getTabAreaInsets();
        Insets insets = getInsets();
        int areaWidth = getWidth() - tabAreaInsets.left - tabAreaInsets.right -
                insets.left - insets.right;
        int tabWidth = 0; // = tabInsets.left + tabInsets.right + 3;
        int gap = 0;

        switch(getTabPlacement()) {
          case LEFT: case RIGHT:
            tabWidth = areaWidth / 4;
            gap = 0;
            break;
          case BOTTOM: case TOP: default:
            tabWidth = areaWidth / tabCount;
            gap = areaWidth - (tabWidth * tabCount);
        } // Fim do switch
        // Define a largura da aba
        tabWidth = tabWidth - tabInsets.left - tabInsets.right - 3; // ESTE 3 DEFINE O TAMANHO FIXO DA ABA!!
        for(int i = 0; i < tabCount; i++) {
            JLabel label = (JLabel)getTabComponentAt(i);
            if(label == null) break;
            label.setPreferredSize(new Dimension(tabWidth + (i < gap ? 1 : 0),
                    label.getPreferredSize().height));
        } // Fim do for
    } // Fim do método initTabWidth

    /** Repinta o componente. */
    @Override
    public synchronized void repaint() {
        initTabWidth();
        super.repaint();
    } // Fim do método repaint

    /**
     * Insere uma nova aba no painel tabulado.
     * 
     * @param titulo o título da aba.
     * @param icone o ícone da aba
     * @param componente o componente da aba.
     * @param tooltip o texto tooltip da aba.
     * @param indice o índice da aba na lista de painéis.
     */
    @Override
    public void insertTab(String titulo, Icon icone, Component componente,
            String tooltip, int indice) {
        super.insertTab(titulo, icone, componente,
                tooltip == null ? titulo : tooltip, indice);
        JLabel label = new JLabel(titulo, JLabel.CENTER);
        Dimension dim = label.getPreferredSize();
        Insets tabInsets = getTabInsets();
        label.setPreferredSize(new Dimension(0, dim.height + tabInsets.top +
                tabInsets.bottom));
        setTabComponentAt(indice, label);
    } // Fim do método insertTab

    
} // Fim da classe MyTabbedPane