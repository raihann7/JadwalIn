package ic.ac.unpas.jadwalin.ui.screens

sealed class NavScreen(val route: String) {
    object Home : NavScreen("home")
    object AddClasses : NavScreen("addClasses")
    object AddLecturer : NavScreen("addLecturer")
    object AddSubject : NavScreen("addSubject")
    object EditClasses : NavScreen("editClasses") {
        const val routeWithArgument: String = "editClasses/{id}"
        const val argument0 : String = "id"
    }
    object EditLecturer : NavScreen("editLecturer") {
        const val routeWithArgument: String = "editLecturer/{id}"
        const val argument0 : String = "id"
    }
    object EditSubject : NavScreen("editSubject") {
        const val routeWithArgument: String = "editSubject/{id}"
        const val argument0 : String = "id"
    }
    object ListClasses : NavScreen("listClasses")
    object ListSubject : NavScreen("listSubject")
    object ListLecturer : NavScreen("listLecturer")
    object Login : NavScreen("login")
}