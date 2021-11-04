package com.example.kepengku;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import me.biubiubiu.justifytext.library.JustifyTextView;

public class Tips1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips1);

        String a ="Keuangan adalah sebuah hal yang sangat penting Anda kelola. Mengelola keuangan memang tidak akan semudah seperti yang Anda lihat di televisi ataupun seminar. Namun bagaimanapun juga Anda tetap harus berusaha untuk mengetahui cara mengelola keuangan dengan baik dan benar.\n" +
                "\n" +
                "Sebenarnya mengelola keuangan ada banyak cara. Anda tentu harus memilih cara manakah yang paling sesuai untuk Anda. Beberapa faktor seperti seberapa besar gaji Anda, seberapa besar pengeluaran Anda, apa saja tanggungan Anda, hingga rencana kedepan Anda akan menentukan bagaimana cara yang tepat untuk mengelola keuangan. Berikut adalah cara mengatur keuangan bulanan yang tepat untuk Anda.\n" +
                "\n"+
                "1. Catat keuangan bulanan\n" +
                "\n" +
                "Pencatatan keuangan bulanan Anda akan membantu Anda mengevaluasi pengelolaan keuangan Anda. Anda akan mengetahui sektor mana saja yang masih bisa Anda hemat dan sektor mana yang lebih bisa Anda prioritaskan.\n" +
                "\n" +
                "2. Pisahkan berbagai kebutuhan\n" +
                "\n" +
                "Pada dasarnya Anda dapat membagi kebutuhan Anda berdasarkan waktunya seperti jangka pendek, menengah, dan masa depan. Kebutuhan jangka pendek tentu harus Anda utamakan dalam pemenuhannya tetapi Anda juga harus mempertimbangkan kebutuhan masa depan Anda.\n" +
                "\n" +
                "3. Kendalikan diri\n" +
                "\n" +
                "Pikiran dan psikologi Anda dalam mengelola uang sangatlah penting untuk diperhatikan. Ada banyak orang yang sangat boros ketika sedang mendapatkan banyak uang. Pada kasus seperti itu sebenarnya pikiran dan psikologi orang yang lebih berperan dalam mengelola keuangan.\n" +
                "\n" +
                "Anda harus bisa menahan diri mengendalikan berbagai keinginan Anda. Pahami lagi mengenai keinginan dan kebutuhan.\n" +
                "\n" +
                "4. Jangan lupa menabung\n" +
                "\n" +
                "Menabung memang sangatlah penting dalam mengelola keuangan dengan benar. Sepertinya semua orang sudah tahu mengenai pentingnya menabung. Tetapi satu hal yang penting adalah Anda sebaiknya tidak menyimpan uang terlalu banyak dalam tabungan.\n" +
                "\n" +
                "Hal tersebut adalah karena uang Anda akan termakan inflasi. Investasi adalah sesuatu yang harus Anda pertimbangkan ketika mengelola keuangan Anda.\n" +
                "\n" +
                "5. Simpan uang receh\n" +
                "\n" +
                "Jangan pernah sepelekan uang receh yang Anda miliki. Anda bisa memanfaatkan celengan agar Anda bisa menyimpan uang receh Anda. Tanpa terasa uang receh yang Anda kumpulkan dapat Anda gunakan untuk keperluan tersier Anda.\n" +
                "\n" +
                "6. Buatlah anggaran keuangan\n" +
                "\n" +
                "Membuat anggaran adalah salah satu cara agar uang dapat Anda gunakan dengan tepat. Usahakan selalu membuat skala prioritas. Skala prioritas akan menghindarkan Anda membeli sesuatu yang tidak Anda butuhkan. Selain itu anggaran keuangan akan memberi gambaran seberapa besar pengeluaran bulanan Anda.\n" +
                "\n" +"\n"+"\n"+
                "Mengelola Keuangan Menurut Orang Sukses\n" +
                "\n" +
                "Warren Buffet yang merupakan pria paling kaya pada nomor tiga pada tahun 2015 mengatakan Anda harus menggunakan uang di bawah pendapatan yang Anda miliki. Jika Anda memiliki pengeluaran yang sama besar atau bahkan lebih besar dari gaji Anda maka keuangan Anda akan hancur cepat atau lambat. Anda tidak akan bisa mengalokasikan uang Anda untuk menabung, asuransi, atau bahkan investasi.\n" +
                "\n" +
                "Jack Ma yang merupakan seorang pengusaha sukses mengatakan jika Anda harus merencanakan masa depan Anda dengan matang. Sukses yang diraih oleh Jack Ma merupakan sesuatu yang sudah direncanakannya sejak lama. Banyak orang yang terlalu fokus akan hari ini dan melupakan masa depan.\n" +
                "\n" +
                "Carlos Slim yang merupakan pengusaha besar asal Amerika Selatan berpendapat jika Anda harus berusaha mengembangkan atau menumbuhkan uang yang Anda miliki. Ada banyak orang yang memiliki tujuan untuk bisa hidup dari pertumbuhan uang yang Anda miliki saat ini.\n" +
                "\n" +
                "Memang uang bukanlah segalanya dalam hidup. Namun cara mengelola keuangan yang tepat akan membantu Anda untuk menata hidup lebih baik. Mengatur keuangan sejak dini akan membantu Anda menyiapkan segala sesuatunya di masa depan. Selain itu Anda juga bisa memastikan kehidupan Anda dapat berjalan dengan baik untuk dirimu, bahkan tidak hanya Anda sendiri tetapi juga orang terdekat.\n" +
                "\n\n\n";

        JustifyTextView justifyTextView=(JustifyTextView)findViewById(R.id.tipiss1);
        justifyTextView.setText(a);

    }
}