package org.iesch.a05_recycler_intermedio.provider

import org.iesch.a05_recycler_intermedio.Model.AndroidVersions

class androidversionProvider {

        companion object {
            val androidVersionsList = listOf<AndroidVersions>(
                AndroidVersions("Android 14", "Upside Down Cake", 34, 2023),
                AndroidVersions("Android 13", "Tiramisu", 33, 2022),
                AndroidVersions("Android 12", "Snow Cone", 31, 2021),
                AndroidVersions("Android 11", "Red Velvet Cake", 30, 2020),
                AndroidVersions("Android 10", "Quince Tart", 29, 2019),
                AndroidVersions("Android 9", "Pie", 28, 2018),
                AndroidVersions("Android 8.1", "Oreo", 27, 2017),
                AndroidVersions("Android 8.0", "Oreo", 26, 2017),
                AndroidVersions("Android 7.1", "Nougat", 25, 2016),
                AndroidVersions("Android 7.0", "Nougat", 24, 2016),
                AndroidVersions("Android 6.0", "Marshmallow", 23, 2015),
                AndroidVersions("Android 5.1", "Lollipop", 22, 2015),
                AndroidVersions("Android 5.0", "Lollipop", 21, 2014),
                AndroidVersions("Android 4.4", "KitKat", 19, 2013),
                AndroidVersions("Android 4.3", "Jelly Bean", 18, 2013),
                AndroidVersions("Android 4.2", "Jelly Bean", 17, 2012),
                AndroidVersions("Android 4.1", "Jelly Bean", 16, 2012)
            )
        }

}