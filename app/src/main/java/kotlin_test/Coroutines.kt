package kotlin_test

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(){
    val start=System.currentTimeMillis()
    runBlocking {

        repeat(100000){
            launch {
                println(1)
            }
        }
    }
    val end=System.currentTimeMillis()
    println(-start+end)
}