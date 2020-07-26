package com.example.tictac

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
  lateinit var btn1:Button
    lateinit var btn2:Button
    lateinit var btn3:Button
    lateinit var btn4:Button
    lateinit var btn5:Button
    lateinit var btn6:Button
    lateinit var btn7:Button
    lateinit var btn8:Button
    lateinit var btn9:Button
    lateinit var  list:Array<Array<Int>>
    lateinit var Redo:ArrayList<chance>
    lateinit var undo:ArrayList<chance>
    lateinit var btnUndo:Button
    var win=-1
    lateinit var btnRestart:Button
    val user1="X"
    val user2="O"
    var turn=0
    var count=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn1=findViewById(R.id.btn1)
        btn2=findViewById(R.id.btn2)
        btn3=findViewById(R.id.btn3)
        btn4=findViewById(R.id.btn4)
        btn5=findViewById(R.id.btn5)
        btn6=findViewById(R.id.btn6)
        btn7=findViewById(R.id.btn7)
        btn8=findViewById(R.id.btn8)
        btn9=findViewById(R.id.btn9)
        btnRestart=findViewById(R.id.btnRestart)
        btnUndo=findViewById(R.id.btnUndo)

        intializeGrid();
        btnRestart.visibility=View.GONE

        btnUndo.setOnClickListener {
            Undo()
        }

        btn1.setOnClickListener{
            chance(0,0,btn1)

        }
        btn2.setOnClickListener{
            chance(0,1,btn2)
        }
        btn3.setOnClickListener{
            chance(0,2,btn3)
        }
        btn4.setOnClickListener{
            chance(1,0,btn4)
        }
        btn5.setOnClickListener{
            chance(1,1,btn5)
        }
        btn6.setOnClickListener{
            chance(1,2,btn6)
        }
        btn7.setOnClickListener{
            chance(2,0,btn7)
        }
        btn8.setOnClickListener{
            chance(2,1,btn8)
        }
        btn9.setOnClickListener{
            chance(2,2,btn9)
        }

        btnRestart.setOnClickListener {
            intializeGrid()
            btnRestart.visibility=View.GONE
        }
        }
    fun intializeGrid(){
        Redo= arrayListOf()
        undo= arrayListOf()
        win=-1
        turn=0
        count=0
        list= arrayOf(arrayOf(-1,-1,-1),
            arrayOf(-1,-1,-1),
            arrayOf(-1,-1,-1))
        btn1.text="_"
        btn2.text="_"
        btn3.text="_"
        btn4.text="_"
        btn5.text="_"
        btn6.text="_"
        btn7.text="_"
        btn8.text="_"
        btn9.text="_"

    }
    fun chance(i:Int,j:Int,view:Button){
        if(list[i][j]==-1 && win==-1){
            val ch=chance(view,turn,i,j)
            undo.add(ch)
            count++
            if(turn==0){
                list[i][j]=0;
                turn=1;

                view.text=user1
            }
            else{
                list[i][j]=1;
                turn=0;
                view.text=user2

            }

            winner()
            if(count==9 && win==-1){
                Toast.makeText(this@MainActivity,"Draw",Toast.LENGTH_LONG).show()
                btnRestart.visibility=View.VISIBLE
            }
        }
    }
    fun winner(){
        for(i in 0 until 3){
            var c=1
            for(j in 0 until 2){
                if(list[i][j]==list[i][j+1] && list[i][j]!=-1){
                    c++;
                }
                else{
                    break;
                }
            }
            if(c==3){
                btnRestart.visibility=View.VISIBLE
                if(turn==1){
                    Toast.makeText(this@MainActivity,"player 1 won", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(this@MainActivity,"player 2 won", Toast.LENGTH_SHORT).show()
                }
                win=1;
            }
        }
        for(i in 0 until 3){
            var c=1
            for(j in 0 until 2){
                if(list[j][i]==list[j+1][i] && list[j][i]!=-1){
                    c++;
                }
                else{
                    break;
                }
            }
            if(c==3){
                btnRestart.visibility=View.VISIBLE
                win=1;
                if(turn==1){
                    Toast.makeText(this@MainActivity,"player 1 won", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(this@MainActivity,"player 2 won", Toast.LENGTH_SHORT).show()
                }

            }
        }
        if(list[0][0]==list[1][1] && list[1][1]==list[2][2] && list[0][0]!=-1){
            win=1;
            btnRestart.visibility=View.VISIBLE
            if(turn==1){
                Toast.makeText(this@MainActivity,"player 1 won", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this@MainActivity,"player 2 won", Toast.LENGTH_SHORT).show()
            }
        }
        else if(list[2][0]==list[1][1] && list[1][1]==list[0][2] && list[0][2]!=-1){
            win=1
            btnRestart.visibility=View.VISIBLE
            if(turn==1){
                Toast.makeText(this@MainActivity,"player 1 won", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this@MainActivity,"player 2 won", Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun Undo(){
        //undo and redo are implemented on stack
        if (!(undo.isEmpty())) {
            if(win==1 || count==9){
                btnRestart.visibility=View.GONE
            }

            val ch = undo.last()
            undo.remove(ch)
            Redo.add(ch)
            turn = ch.turn
            ch.button.text = "_"
            list[ch.i][ch.j] = -1
            count--;

            win = -1
        }
    }
}
