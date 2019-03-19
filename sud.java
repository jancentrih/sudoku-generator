/**
 *
 * @author jancentrih
 * https://github.com/jancentrih
 */

class Sudoku{
    public int[][] stevila;
    private boolean[][] zacetna;
    
    public Sudoku(){
        this.stevila = new int[9][9];
        this.zacetna = new boolean[9][9];
        
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                zacetna[i][j] = false;
            }
        }
    }
    
    public void zacetna(){
        int stz = 0;
        
        while(stz<20){
            int rv = (int)(Math.random()* 8);
            int rs = (int)(Math.random()* 8);
            int rst = 1+(int)(Math.random()* 9);
            
            if(vstavi(rst,rv,rs) && (stevila[rv][rs]==0)){
                this.stevila[rv][rs] = rst;
                this.zacetna[rv][rs] = true;
                stz++;
            }
        }
        
    }
    
    public boolean vstavi(int st, int vrsta, int stolp){
        boolean rez = true;
        
        for(int j=0;j<9;j++){
            if(this.stevila[vrsta][j] == st){
                rez = false;
            }
        }
        
        for(int l=0;l<9;l++){
            if(this.stevila[l][stolp] == st){
                rez = false;
            }
        }
        if(vrsta<3 && stolp <3){
            for(int i=0;i<3;i++){
                for(int k=0;k<3;k++){
                    if(this.stevila[i][k] == st){
                        rez = false;
                    }
                }
            }
        }
        else if(vrsta<3 && (stolp>2 && stolp <6)){
            for(int i=0;i<3;i++){
                for(int k=3;k<6;k++){
                    if(this.stevila[i][k] == st){
                        rez = false;
                    }
                }
            }
        }
        else if(vrsta<3 && stolp>5){
            for(int i=0;i<3;i++){
                for(int k=6;k<9;k++){
                    if(this.stevila[i][k] == st){
                        rez = false;
                    }
                }
            }
        }
        
        else if((vrsta>2 && vrsta<6) && stolp <3){
            for(int i=3;i<6;i++){
                for(int k=0;k<3;k++){
                    if(this.stevila[i][k] == st){
                        rez = false;
                    }
                }
            }
        }
        else if((vrsta>2 && vrsta<6) && (stolp>2 && stolp <6)){
            for(int i=3;i<6;i++){
                for(int k=3;k<6;k++){
                    if(this.stevila[i][k] == st){
                        rez = false;
                    }
                }
            }
        }
        else if((vrsta>2 && vrsta<6) && stolp>5){
            for(int i=3;i<6;i++){
                for(int k=6;k<9;k++){
                    if(this.stevila[i][k] == st){
                        rez = false;
                    }
                }
            }
        }
        
        
        else if(vrsta>5 && stolp <3){
            for(int i=6;i<9;i++){
                for(int k=0;k<3;k++){
                    if(this.stevila[i][k] == st){
                        rez = false;
                    }
                }
            }
        }
        else if(vrsta>5 && (stolp>2 && stolp <6)){
            for(int i=6;i<9;i++){
                for(int k=3;k<6;k++){
                    if(this.stevila[i][k] == st){
                        rez = false;
                    }
                }
            }
        }
        else if(vrsta>5 && stolp>5){
            for(int i=6;i<9;i++){
                for(int k=6;k<9;k++){
                    if(this.stevila[i][k] == st){
                        rez = false;
                    }
                }
            }
        }
        
        return rez;
    }
    
    public void izpisi(){
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if(zacetna[i][j]){
                    System.out.printf("*%d* ", stevila[i][j]);
                }else{
                    System.out.printf(" %d  ", stevila[i][j]);
                }
                if(j==2 || j==5){
                    System.out.print(" | ");
                }
            }
            System.out.println("");
            if(i==2 || i==5){
                System.out.println("-----------------------------------------");
            }
        }
    }
    
    public boolean jeResen(){
        boolean rez = true;
        
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if(this.stevila[i][j] == 0){
                    rez = false;
                }
            }
        }
        
        return rez;
    }
    
    public boolean resuj(){
        boolean res = jeResen();
        if(res==true){
            return true;
        }
        
        int v=0;
        int s=0;
        int prazni=0;
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if(this.stevila[i][j] == 0){
                    prazni++;
                    v =i;
                    s=j;
                    break;
                }
            }
            if(prazni>0){
                prazni=0;
                break;
            }
        }
        
        for(int k=1;k<10;k++){
            boolean lahko = vstavi(k, v, s);
            if(lahko == true){
                this.stevila[v][s] = k;
                boolean tmp = this.resuj();
                if(tmp == true){
                    return true;
                }else{
                    //postavim nazaj na 0
                    this.stevila[v][s] = 0;
                }
            }
        }
        return false;
    }
}

public class sud {
    public static void main(String[] args) {
        Sudoku s = new Sudoku();
        System.out.println("STARTING PUZZLE (starting values are marked with stars *x*):");
        s.zacetna();
        s.izpisi();
        System.out.println("----------------------------------------------------");
        boolean resen = s.resuj();
        
        if(resen){
            System.out.println("THE SOLUTION (starting values are marked with stars *x*):");
            s.izpisi();
        }else{
            System.out.println("SUDOKU NOT SOLVABLE (with this random seed)");
        }
    }
}
