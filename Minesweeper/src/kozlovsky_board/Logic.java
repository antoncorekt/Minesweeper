package kozlovsky_board;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by Антон on 01.04.2016.
 */
public class Logic implements Serializable {

    public final int Mina = -1, None=0, DontTouch = -2, CheckMina = -3, Opened = -4, NotEnaeble = -5;

    public int [][]x;
    public int [][]state;
    public int c_mine;


    public Logic(int level, int level1)
    {
        x= new int[level+2][level1+2];
    }

    public Logic(int level, int level1, int count_mine, int notI, int notJ)
    {
        x= new int[level+2][level1+2];
        state= new int[level+2][level1+2];

        c_mine = count_mine;

        for (int i = 0; i < level+2; i++) {
            for (int j = 0; j < level1+2; j++) {
                x[i][j] = state[i][j] = None;
            }
        }

        Random rand = new Random();
        for (int i = 0; i < count_mine; i++) {
            int a = rand.nextInt(level)+1, b = rand.nextInt(level1)+1;
            if ((x[a][b] == Mina)||(a==notI+1 && b==notJ+1))
                i--;
            else {
                System.out.println("new mina " + a + " ; " + b);
                x[a][b] = Mina;
            }

        }

        for (int i = 1; i < level+1; i++) {
            for (int j = 1; j < level1+1; j++) {
                if (x[i][j] != Mina)
                x[i][j] = getNum(i,j);
            }
        }
    }

   public boolean isMine(int i, int j)
   {
       return x[i+1][j+1]==Mina;
   }

    public int getNum(int a, int b)
    {
        int sum=0;

        for (int i=a-1;i<=a+1;i++)
        {
            for (int j=b-1;j<=b+1;j++)
            {
                if ((i==a)&&(j==b))
                {
                    continue;
                }
                else
                    if (x[i][j] == Mina)
                        sum ++;
            }
        }

        return sum;
    }

    public void setState(int i, int j, int st)
    {
        state[i+1][j+1] = st;
    }

    public int getState(int i, int j) {return state[i+1][j+1];}

    public boolean win()
    {
        for (int i = 0; i < x.length; i++)
            for (int j = 0; j < x[0].length; j++)
                if (x[i][j]==Mina && state[i][j]!=CheckMina)
                    return false;
        return true;
    }

    public boolean canOpenField(int i, int j)
    {
        state[i+1][j+1] = NotEnaeble;
        return x[i+1][j+1]==None;
    }

    public void OpenField(int i, int j) {


        if (i==0 || i == x.length || j==0 || x[0].length==0) {

            //state[i][j] = NotEnaeble;
            return;

        }
        if (i-1!=0)
            if (x[i-1][j]==None &&  state[i-1][j]!=NotEnaeble)
            {
            state[i][j] = NotEnaeble;

                OpenField(i-1,j);
            }
        if (i+1!=x.length)
            if (x[i+1][j]==None &&  state[i+1][j]!=NotEnaeble)
            {
                state[i][j] = NotEnaeble;

                OpenField(i+1,j);
            }
        if (j-1!=0)
            if (x[i][j-1]==None &&  state[i][j-1]!=NotEnaeble)
            {
                state[i][j] = NotEnaeble;

                OpenField(i,j-1);
            }
        if (j+1!=x[0].length)
            if (x[i][j+1]==None &&  state[i][j+1]!=NotEnaeble)
            {
                state[i][j] = NotEnaeble;

                OpenField(i,j+1);
            }
        state[i][j] = NotEnaeble;
    }

    public void openedNotEnaeble()
    {
        for (int i = 1; i < x.length-1; i++)
            for (int j = 1; j < x[0].length-1; j++)
                if (state[i][j]==NotEnaeble)
                    for (int k=i-1;k<=i+1;k++)
                        for (int t=j-1;t<=j+1;t++)
                            if (state[k][t]!=NotEnaeble && x[k][t]>0)
                                state[k][t] = Opened;

    }
}
