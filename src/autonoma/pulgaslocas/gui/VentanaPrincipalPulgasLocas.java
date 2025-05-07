package autonoma.pulgaslocas.gui;

import autonoma.pulgaslocas.elements.CampoDeBatalla;
import autonoma.pulgaslocas.elements.PulgaMutante;
import autonoma.pulgaslocas.elements.PulgaNormal;
import autonoma.pulgaslocas.elements.Soldado;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;

public class VentanaPrincipalPulgasLocas extends javax.swing.JFrame {

    private CampoDeBatalla campo;
    private Soldado soldado;
    private JPanel gamePanel;
    private Timer gameTimer;
    private Timer pulgaNormalTimer;
    private Timer pulgaMutanteTimer;

    public VentanaPrincipalPulgasLocas(CampoDeBatalla campo, Soldado soldado) throws IOException {
        this.campo = campo;
        this.soldado = soldado;
        initComponents(); // Llamada al método generado, pero no mostraremos su contenido
        this.mostrarPuntajes();
        initGamePanel();
        startGameLoop();
        startAutomaticPulgaAddition();
    }

    private void initGamePanel() {
        gamePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawGame(g);
            }
        };

        gamePanel.setBounds(0, 0, 700, 500);
        gamePanel.setFocusable(true);
        gamePanel.requestFocusInWindow();

        gamePanel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        gamePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (soldado != null) {
                    if (e.getButton() == MouseEvent.BUTTON1) {
                        soldado.dispararPistolaPulguiplum(e);
                    } else if (e.getButton() == MouseEvent.BUTTON3) {
                        soldado.dispararMisilPulgoson(e);
                    }
                    repaint();
                }
            }
        });

        getContentPane().add(gamePanel, 0);
        campo.setBounds(gamePanel.getWidth(), gamePanel.getHeight()); // Inicializar límites del campo
        if (soldado != null) {
            soldado.inicializarLimites(gamePanel.getWidth(), gamePanel.getHeight());
        }
    }

    private void startGameLoop() {
        gameTimer = new Timer(16, e -> {
            gamePanel.repaint();
        });
        gameTimer.start();
    }

    private void startAutomaticPulgaAddition() {
        pulgaNormalTimer = new Timer(5000, e -> {
            campo.agregarPulgaNormalAutomatica();
        });
        pulgaNormalTimer.start();

        pulgaMutanteTimer = new Timer(10000, e -> {
            campo.agregarPulgaMutanteAutomatica();
        });
        pulgaMutanteTimer.start();
    }

    private void drawGame(Graphics g) {
        if (soldado != null) {
            soldado.draw(g);
        }

        for (PulgaNormal pulga : campo.getPulgasNormales()) {
            pulga.draw(g);
        }

        for (PulgaMutante pulga : campo.getPulgasMutantes()) {
            pulga.draw(g);
        }
    }



    public void setCampoDeBatalla(CampoDeBatalla campo) {
        this.campo = campo;
    }

    public void mostrarPuntajes() throws IOException {
        String ruta = "puntajes.txt";
        CampoDeBatalla campoPuntajes = new CampoDeBatalla(ruta); // Usar una instancia separada para leer puntajes

        ArrayList<Integer> puntajes = campoPuntajes.leerPuntajes();
        StringBuilder sb = new StringBuilder();

        for (int p : puntajes) {
            sb.append(p).append("<br>"); // Usamos HTML para saltos de línea
        }

        this.Puntajeslbl.setText("<html>" + sb.toString() + "</html>");
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        PanelComandos = new javax.swing.JPanel();
        TitulosComandoslbl = new javax.swing.JLabel();
        Comandoslbl = new javax.swing.JLabel();
        PanelPuntajes = new javax.swing.JPanel();
        TituloPuntajeslbl = new javax.swing.JLabel();
        Puntajeslbl = new javax.swing.JLabel();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        PanelComandos.setBackground(new java.awt.Color(0, 102, 0));
        PanelComandos.setPreferredSize(new java.awt.Dimension(200, 250));

        TitulosComandoslbl.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        TitulosComandoslbl.setForeground(new java.awt.Color(255, 255, 255));
        TitulosComandoslbl.setText("COMANDOS");

        Comandoslbl.setText("Pulga normal -->");

        javax.swing.GroupLayout PanelComandosLayout = new javax.swing.GroupLayout(PanelComandos);
        PanelComandos.setLayout(PanelComandosLayout);
        PanelComandosLayout.setHorizontalGroup(
            PanelComandosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelComandosLayout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addComponent(TitulosComandoslbl)
                .addGap(27, 27, 27))
            .addGroup(PanelComandosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Comandoslbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        PanelComandosLayout.setVerticalGroup(
            PanelComandosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelComandosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TitulosComandoslbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Comandoslbl)
                .addContainerGap(190, Short.MAX_VALUE))
        );

        PanelPuntajes.setBackground(new java.awt.Color(0, 153, 51));
        PanelPuntajes.setForeground(new java.awt.Color(0, 153, 0));
        PanelPuntajes.setPreferredSize(new java.awt.Dimension(200, 250));

        TituloPuntajeslbl.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        TituloPuntajeslbl.setForeground(new java.awt.Color(255, 255, 255));
        TituloPuntajeslbl.setText("PUNTAJES");

        javax.swing.GroupLayout PanelPuntajesLayout = new javax.swing.GroupLayout(PanelPuntajes);
        PanelPuntajes.setLayout(PanelPuntajesLayout);
        PanelPuntajesLayout.setHorizontalGroup(
            PanelPuntajesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelPuntajesLayout.createSequentialGroup()
                .addContainerGap(40, Short.MAX_VALUE)
                .addComponent(TituloPuntajeslbl)
                .addGap(41, 41, 41))
            .addGroup(PanelPuntajesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Puntajeslbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        PanelPuntajesLayout.setVerticalGroup(
            PanelPuntajesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelPuntajesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TituloPuntajeslbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Puntajeslbl, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 700, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PanelComandos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PanelPuntajes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(PanelPuntajes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(PanelComandos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_RIGHT:
                this.soldado.mover(evt);
                break;

            case KeyEvent.VK_M:
            {
            try {
                campo.handleKey(evt);
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(VentanaPrincipalPulgasLocas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
            }
                break;


            case KeyEvent.VK_N:
            {
            try {
                campo.handleKey(evt);
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(VentanaPrincipalPulgasLocas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
            }
            break;


            case KeyEvent.VK_B:
            {
                try {
                    campo.handleKey(evt);
                } catch (IOException ex) {
                }
            }
            break;
                
            case KeyEvent.VK_Q:
            {
                try {
                    campo.handleKey(evt);
                    this.exitGame();
                } catch (IOException ex) {
                }
            }
            break;
        }
        repaint();
    }//GEN-LAST:event_formKeyPressed

    private void exitGame() {
        System.exit(0);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Comandoslbl;
    private javax.swing.JPanel PanelComandos;
    private javax.swing.JPanel PanelPuntajes;
    private javax.swing.JLabel Puntajeslbl;
    private javax.swing.JLabel TituloPuntajeslbl;
    private javax.swing.JLabel TitulosComandoslbl;
    private javax.swing.JMenuItem jMenuItem1;
    // End of variables declaration//GEN-END:variables
}
