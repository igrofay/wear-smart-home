package com.example.smarthome.presentation.nav.view

import androidx.navigation.*
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.smarthome.presentation.device_control.view.DeviceControlScreen
import com.example.smarthome.presentation.list_device.view.RoomListDeviceScreen
import com.example.smarthome.presentation.list_room.view.ListRoomScreen
import com.example.smarthome.presentation.nav.model.MainRouting

fun NavGraphBuilder.mainGraph(navController: NavController){
    navigation(
        startDestination = MainRouting.ListRoom.route,
        route = MainRouting.route
    ) {
        composable(MainRouting.ListRoom.route){
            ListRoomScreen(
                openRoom = {nameRoom ->
                    navController.navigate(
                        MainRouting.ListDevice.allRoute(nameRoom),
                    ){ popUpTo(MainRouting.ListRoom.route) }
                }
            )
        }
        composable(
            MainRouting.ListDevice.allRoute(),
            arguments = listOf(
                navArgument(MainRouting.ListDevice.arg1) { type = NavType.StringType }
            )
        ){navBackStack ->
            val nameRoom = navBackStack.arguments!!.getString(MainRouting.ListDevice.arg1)!!
            RoomListDeviceScreen(
                openDevice = { nameDevice ->
                    navController.navigate(
                        MainRouting.DeviceControl.allRoute(nameRoom, nameDevice),
                    ){ popUpTo(MainRouting.ListDevice.allRoute()) }
                }
            )
        }
        composable(
            MainRouting.DeviceControl.allRoute(),
            arguments = listOf(
                navArgument(MainRouting.DeviceControl.arg1) { type = NavType.StringType },
                navArgument(MainRouting.DeviceControl.arg2) { type = NavType.StringType },
            )
        ){
            DeviceControlScreen()
        }
    }
}