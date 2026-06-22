import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

// ex 1
class Preparat {
    private String denumire;
    private double pret;

    public Preparat(String denumire, double pret) {
        this.denumire = denumire;
        this.pret = pret;
    }

    public String getDenumire() { return denumire; }
    public void setDenumire(String denumire) { this.denumire = denumire; }
    public double getPret() { return pret; }
    public void setPret(double pret) { this.pret = pret; }

    @Override
    public String toString() {
        return denumire + " - " + pret + " lei";
    }
}

// ex 2
class Meniu {
    protected String nume;
    protected double pretDeBaza;

    public Meniu(String nume, double pretDeBaza) {
        this.nume = nume;
        this.pretDeBaza = pretDeBaza;
    }

    public String getNume() { return nume; }
    public void setNume(String nume) { this.nume = nume; }
    public double getPretDeBaza() { return pretDeBaza; }
    public void setPretDeBaza(double pretDeBaza) { this.pretDeBaza = pretDeBaza; }
}

// ex 3
class MeniuZilnic extends Meniu {
    private final String ziuaSaptamanii;

    // ex 4
    private final List<Preparat> preparate;

    public MeniuZilnic(String nume, double pretDeBaza, String ziuaSaptamanii) {
        super(nume, pretDeBaza);
        this.ziuaSaptamanii = ziuaSaptamanii;
        this.preparate = new ArrayList<>();
    }

    // ex 5 a)
    public void adaugaPreparat(Preparat p) {
        this.preparate.add(p);
    }

    // ex 5 b)
    public double getPretTotal() {
        double total = this.pretDeBaza;
        for (Preparat p : preparate) {
            total += p.getPret();
        }
        return total;
    }

    public List<Preparat> getPreparate() {
        return preparate;
    }

    @Override
    public String toString() {
        return "Meniu: " + getNume() + " (" + ziuaSaptamanii + ")";
    }
}

// ex 6
public class Main extends JFrame {
    private final MeniuZilnic meniuMiercuri;

    // Componente interfață
    private JLabel labelTitlu;
    private JList<String> listaComponenta;
    private JButton butonCalcul;
    private JLabel labelRezultat;

    public Main() {
        // ex 6
        meniuMiercuri = new MeniuZilnic("Meniu Special", 12.0, "Miercuri");
        meniuMiercuri.adaugaPreparat(new Preparat("Supă-cremă de ciuperci", 16.0));
        meniuMiercuri.adaugaPreparat(new Preparat("Pui la grill cu cartofi", 28.5));
        meniuMiercuri.adaugaPreparat(new Preparat("Tiramisu", 14.0));

        //  configurare ferestra grafica
        initComponents();
    }

    private void initComponents() {
        setTitle("Gestiune Restaurant - NetBeans GUI");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // titlu
        labelTitlu = new JLabel(meniuMiercuri.toString(), SwingConstants.CENTER);
        labelTitlu.setFont(new Font("Tahoma", Font.BOLD, 14));
        add(labelTitlu, BorderLayout.NORTH);

        // preparate
        DefaultListModel<String> model = new DefaultListModel<>();
        for (Preparat p : meniuMiercuri.getPreparate()) {
            model.addElement(p.toString());
        }
        listaComponenta = new JList<>(model);
        add(new JScrollPane(listaComponenta), BorderLayout.CENTER);

        JPanel panelJos = new JPanel(new GridLayout(2, 1, 5, 5));
        butonCalcul = new JButton("Calculează Preț Total");
        labelRezultat = new JLabel("Preț total: - lei", SwingConstants.CENTER);
        labelRezultat.setFont(new Font("Tahoma", Font.BOLD, 12));


        butonCalcul.addActionListener(evt -> {
            double pretTotal = meniuMiercuri.getPretTotal();
            labelRezultat.setText("Preț total: " + pretTotal + " lei (bază: " + meniuMiercuri.getPretDeBaza() + " lei)");
        });

        panelJos.add(butonCalcul);
        panelJos.add(labelRezultat);
        add(panelJos, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new Main().setVisible(true));
    }
}