package org.brometheus.pokesim;

/**
 * Created by Kalle on 14.1.2015.
 */
public class Pokemon {


    //initializing stats
    private int species;
    private String name;
    private char[] ivs;
    private char[] evs;
    private int stats[]={0,0,0,0,0,0,1,1};
    private int statsCurrent[];
    private int[] moves;
    private char[] status;
    private int item;
    private int level;
    private double[]statmod={1,1,1,1,1,1};
    //setting pokemon type to 18, which is ??? - all damages normally
    private char type[]={18,18};


    //constructor without a nickname; nickname is empty.
    public Pokemon(int species, char[] ivs, char[]evs, int item, int[]moves,char nature)
    {
        //atk-def-spa-spd-spe

        //first setting of nature that will effect pokemon's stats.
        //25 natures, of which 20 do something.
        //atk-def-spa-spd-spe increase, with decrease similarly
        if (nature<20){
            double increase=nature/4;
            statmod[(int)increase+1]=1.1;
            int decrease = nature%4; //gives 0...4 - the stat that is decreased
            if (decrease>=increase)
                decrease++;
            statmod[decrease+1]=0.9;
        }

        this.species=species;
        //get species name from database.
        this.name=pokemonData.getName(species);
        //get base stats from database.
        char[]basestats=pokemonData.getBaseStats(species);
        type=getTypes(species);


        //setting HP; first checking if the pokemon is Shedinja who has 1 hp
        //if not, then set hp normally
        if (this.species == 292)
            this.stats[0]=1;
        else
            this.stats[0]=(ivs[0]+2*basestats[0]+evs[0]/4+100)*level/100+10;
        this.statsCurrent[0]=this.stats[0];
        for (int i =1; i<6;i++)
        {
            this.stats[i]=(int)(((ivs[i]+2*basestats[i]+evs[i]/4)*level/100+5)*statmod[i]);
            this.statsCurrent[i]=this.stats[i];
        }

        this.moves=moves;
        this.item=item;
    }

    //constructor with a nickname; nickname is given
    public Pokemon(int species, char[] ivs, char[]evs, int item, int[]moves,char nature,String name)
    {
        //atk-def-spa-spd-spe

        //first setting of nature that will effect pokemon's stats.
        //25 natures, of which 20 do something.
        //atk-def-spa-spd-spe increase, with decrease similarly
        if (nature<20){
            double increase=nature/4;
            statmod[(int)increase+1]=1.1;
            int decrease = nature%4; //gives 0...4 - the stat that is decreased
            if (decrease>=increase)
                decrease++;
            statmod[decrease+1]=0.9;
        }

        this.species=species;
        this.name=name;

        //get base stats from database.
        char[]basestats=pokemonData.getBaseStats(species);
        type=getTypes(species);
        //setting HP; first checking if the pokemon is Shedinja who has 1 hp
        //if not, then set hp normally
        if (this.species == 292)
            this.stats[0]=1;
        else
            this.stats[0]=(ivs[0]+2*basestats[0]+evs[0]/4+100)*level/100+10;
        this.statsCurrent[0]=this.stats[0];
        for (int i =1; i<6;i++)
        {
            this.stats[i]=(int)(((ivs[i]+2*basestats[i]+evs[i]/4)*level/100+5)*statmod[i]);
            this.statsCurrent[i]=this.stats[i];
        }

        this.moves=moves;
        this.item=item;
    }

    //when a pokemon hits another, this is called
    public void hit (int power,double accuracy,int attack ,boolean special, char status, char percentage, char type,double critchance)
    {


    }




}
