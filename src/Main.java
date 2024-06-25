import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    // Construtor para carregar a imagem de fundo
    public BackgroundPanel(String fileName) {
        try {
            backgroundImage = ImageIO.read(new File(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Definir layout nulo para permitir posicionamento absoluto
        setLayout(null);
    }

    // Sobrescrever o método paintComponent para desenhar a imagem de fundo
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        // Criando um JFrame
        JFrame frame = new JFrame("Minha Aplicação");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Criando um JPanel personalizado com uma imagem de fundo
        BackgroundPanel panel = new BackgroundPanel("src/back.jpg");

        // Criando e configurando um JLabel
        JLabel label = new JLabel("Vidas: 3, Score: 1500");
        label.setBounds(100, 50, 200, 30); // Definindo a posição e o tamanho do JLabel
       label.setForeground(Color.YELLOW);
        panel.add(label);

        // Criando um JButton
        JButton button = new JButton("Clique Aqui");
        button.setBounds(100, 150, 150, 30); // Definindo a posição e o tamanho do JButton
        panel.add(button);

        // Adicionando um ActionListener ao JButton
        button.addActionListener(new ActionListener() {
            private int vidas = 3;
            private int score = 1500;
            private int x=100;
            private int y=100;

            @Override
            public void actionPerformed(ActionEvent e) {
                // Atualizando as variáveis
                vidas--;
                score += 100;

                // Atualizando o texto do JLabel
                label.setText("Vidas: " + vidas + ", Score: " + score);
                label.setBounds(x, y, 200, 30);
            }
        });

        // Adicionando o JPanel ao JFrame
        frame.add(panel);

        // Tornando o JFrame visível
        frame.setVisible(true);
    }
}
