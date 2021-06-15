import java.util.*;

public class Plakatowanie_Rafal_Balcerzak {

    static class Budynek {
        int wysokosc;

        public Budynek(int wysokosc) {
            this.wysokosc = wysokosc;
        }

        public int getWysokosc() {
            return wysokosc;
        }

    }

    static Scanner input = new Scanner(System.in);
    static int n;
    static int wysokosc;
    static Budynek maxBudynek;
    List<Budynek> budynekList = new ArrayList<>();
    static int ilePlakatow = 0;
    static int maxWysokosc;


    public static void main(String[] args) {
        new Plakatowanie_Rafal_Balcerzak().run();
    }

    private void run() {
        n = input.nextInt();
        for (int i = 0; i < n; i++) {
            input.nextInt();
            wysokosc = input.nextInt();
            budynekList.add(new Budynek(wysokosc));
        }

        obnizBudynek();
        System.out.println(ilePlakatow);
    }

    public void scalBudynki() {
        int aktualny;
        int nastepny;

        for (int i = 0; i < budynekList.size() - 1; i++) {
            aktualny = budynekList.get(i).wysokosc;
            nastepny = budynekList.get(i + 1).wysokosc;

            if (aktualny == nastepny) {
                budynekList.remove(i);
                i--;
            }
        }
    }

    public void obnizBudynek() {

        int wyzszyObok;
        int aktualny;
        int poprzedni;
        int nastepny;

        while (true) {
            scalBudynki();
            maxBudynek = budynekList.stream().max(Comparator.comparing(Budynek::getWysokosc)).orElseThrow(NoSuchElementException::new);
            maxWysokosc = maxBudynek.wysokosc;
            if (budynekList.size() == 1) {
                ilePlakatow++;
                break;
            }
            for (int i = 0; i < budynekList.size(); i++) {
                aktualny = budynekList.get(i).wysokosc;
                if (i == 0) {
                    nastepny = budynekList.get(i + 1).wysokosc;
                    if (aktualny == maxWysokosc) {
                        budynekList.get(i).wysokosc = nastepny;
                        ilePlakatow++;
                    }

                } else if (i == budynekList.size() - 1) {
                    poprzedni = budynekList.get(i - 1).wysokosc;
                    if (aktualny == maxWysokosc) {
                        budynekList.get(i).wysokosc = poprzedni;
                        ilePlakatow++;
                    }
                } else {
                    poprzedni = budynekList.get(i - 1).wysokosc;
                    nastepny = budynekList.get(i + 1).wysokosc;
                    if (aktualny == maxWysokosc) {
                        wyzszyObok = Math.max(poprzedni, nastepny);
                        budynekList.get(i).wysokosc = wyzszyObok;
                        ilePlakatow++;

                    }
                }
            }
        }
    }


}
