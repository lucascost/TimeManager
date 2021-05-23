package com.webdicas.timemanager
import android.widget.TextView
import java.util.*

fun convert(h:Int,m:Int):Int{
    return h*3600000+m*60000
}
fun format(h:Int,m:Int):String{
    return String.format(Locale.getDefault(),"%02d:%02d", h, m)
}

fun formatFull(tempo:Int):String {
    val ts = tempo/1000
    val h = ts/3600 as Int
    val min = (ts-(3600*h))/60 as Int
    val sec = ts-(h*3600+min*60) as Int
    var timeLeftFormated = ""
    if(ts>=3600)
        timeLeftFormated = String.format(
            Locale.getDefault(),
            "%02d:%02d:%02d",
            h,
            min,
            sec
        )
    else
        timeLeftFormated = String.format(
            Locale.getDefault(),
            "%02d:%02d",
            min,
            sec
        )
    //todo ver pra q serve o locale
    return timeLeftFormated
}

//todo: media: bloquear o botao de adicionar enquanto o tempo não for configurado