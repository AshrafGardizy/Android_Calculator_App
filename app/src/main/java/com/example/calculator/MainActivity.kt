package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.View
import android.widget.EditText
import org.mariuszgromada.math.mxparser.*

//This is a simple calculator using Kotlin
//Operations: +,-,*,/,(),^
//Developed by Ashraf Gardizy
class MainActivity : AppCompatActivity() {
   lateinit var edInput : EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        edInput = findViewById<EditText>(R.id.editTextInput)
        //hide the keyboar
        edInput.showSoftInputOnFocus = false
        edInput.setOnClickListener {
            if(getString(R.string.display).equals(edInput.text.toString()))
            {
                edInput.text.clear()
            }
        }
    }

    //Update Text
    fun updateText(strToAdd:String)
    {
        val input = edInput.text.toString()
        //Cursor position
        var cursorPosition:Int = edInput.selectionStart
        var leftString:String = input.substring(0,cursorPosition)
        var rightString:String = input.substring(cursorPosition)
        if(getString(R.string.display).equals(edInput.text.toString())){
            edInput.setText(strToAdd)
            edInput.setSelection(cursorPosition+1)
        }else{
            edInput.setText(String.format("%s%s%s",leftString,strToAdd,rightString))
            edInput.setSelection(cursorPosition+1)
        }
    }
    //Zero Button
    fun zeroBtnClicked(view: View){
         updateText("0")
    }

    //One Button
    fun oneBtnClicked(view: View){
        updateText("1")
    }

    //Two Button
    fun twoBtnClicked(view: View){
        updateText("2")
    }

    //Three Button
    fun threeBtnClicked(view: View){
        updateText("3")
    }

    //Four Button
    fun fourBtnClicked(view: View){
        updateText("4")
    }

    //Five Button
    fun fiveBtnClicked(view: View){
        updateText("5")
    }

    //Six Button
    fun sixBtnClicked(view: View){
        updateText("6")
    }

    //Seven Button
    fun sevenBtnClicked(view: View){
        updateText("7")
    }

    //Eight Button
    fun eightBtnClicked(view: View){
        updateText("8")
    }

    //Nine Button
    fun nineBtnClicked(view: View){
        updateText("9")
    }

    //Point Button
    fun pointBtnClicked(view: View){
        updateText(".")
    }


    //Add Button
    fun addBtnClicked(view: View){
        updateText("+")
    }

    //Subtract Button
    fun subtracBtnClicked(view: View){
        updateText("-")
    }

    //Multiply Button
    fun multiplyBtnClicked(view: View){
        updateText("x")
    }

    //Divide Button
    fun divideBtnClicked(view: View){
        updateText("÷")
    }

    //Exponent Button
    fun exponentBtnClicked(view: View){
        updateText("^")
    }
    //PlusAndMinus Button
    fun plusAndMinusBtnClicked(view: View){
        updateText("-")
    }

    //Clear Button
    fun clearBtnClicked(view: View){
        edInput.text.clear()
    }

    //Backspace Button
    fun backSpaceBtnClicked(view: View){
        var cursorPosition:Int = edInput.selectionStart
        var inputLength = edInput.text.toString().length
        if(cursorPosition != 0 && inputLength != 0)
        {
            var selection:SpannableStringBuilder = edInput.text as SpannableStringBuilder
            selection.replace(cursorPosition-1,cursorPosition,"")
            edInput.setText(selection)
            edInput.setSelection(cursorPosition - 1)

        }
    }

    //Equal Button
    fun equalsBtnClicked(view: View){
        var inputExpression = edInput.text.toString()
        inputExpression = inputExpression.replace("÷","/")
        inputExpression = inputExpression.replace("x","*")
        var exp:Expression = Expression(inputExpression)
        var result:String = exp.calculate().toString()
        edInput.setText(result)
        edInput.setSelection(result.length)
    }

    //Paranthesi Button
    @OptIn(ExperimentalStdlibApi::class)
    fun paranthesisBtnClicked(view: View){
        var cursorPositon:Int = edInput.selectionStart
        var openParentes = 0;
        var closeParentes = 0
        var textLenght = edInput.text.toString().length
        for (i in 0..<cursorPositon step 1)
        {
            if(edInput.text.toString().substring(i,i+1).equals("("))
            {
                openParentes+=1
            }
            if(edInput.text.toString().substring(i,i+1).equals(")"))
            {
                closeParentes+=1
            }
        }
        if(openParentes == closeParentes || edInput.text.toString().substring(textLenght-1,textLenght).equals("(")){
            updateText("(")
            edInput.setSelection(cursorPositon+1)
        }
        else if( closeParentes <openParentes && !edInput.text.toString().substring(textLenght-1,textLenght).equals("(")){
            updateText(")")
            edInput.setSelection(cursorPositon+1)
        }
    }
}