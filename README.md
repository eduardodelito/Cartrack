# Cartrack - Demo App

Cartrack is using Model-View-ViewModel (ie MVVM) template client application architecture with modularised dependencies. Used LiveData to ensures UI matches on data state and Data Binding to display the details.
This is based on the Guide to app architecture article with the androidx package, Kotlin 1.3 and production ready coroutine. I use several android architecture components like LiveData, ViewModel ,Room. Here are several libraries below:

# Features
 * Display Login Page with (User Name, Password and Create Account CTA)
 * Create Account Page with (First Name, Last Name, User Name, Password, Confirm password and Country)
 * Country dialog selection.
 * User List with (Name, User Name and Contact Number)
 * Progress bar while loading list.
 * Swipe screen to refresh list.
 * Details with (Name, User Name, Email, Address:(Street, Suite, City, Zip Code), Location: (Lat, Lng), Phone, Website, Company: (Name and details))
 * User google map location (Display Name and Address)
 
# Dependency modules
 * buildSrc - Initialize all dependency libraries and versions.
 * cartrack-client - API request and parsing response and save it into local database.
 * cartrack-common - shared classes and functions.
 * cartrack-db - database to save data response.
 * cartrack-ui - To display API response/database into UI.


# Major libraries
 * Navigation - To navigate screen
 * Material - For Android-oriented design
 * LiveData - For lifecycle-aware
 * ViewModel - To outlive specific instantiations of views or LifecycleOwners 
 * Room - database storage
 * Retrofit - Networking
 * Dagger2 - For injection
 * Coroutine - For asynchronous
 * Google Map API
 * Mockito - Unit Test(To Do)
 * Espresso - Unit Test(To Do)
 
 Used MVVM because it is an alternative to MVC and MVP patterns when using Data Binding technology. The ViewModel has Built in LifeCycleOwner and doesn't have a reference for View.
 
 [Debug Cartrack APK here!](https://drive.google.com/file/d/1hA7XYxQDYlB9W0Dh5Uf_0DDB8duc60yy/view?usp=sharing)
 
 <b>Mobile Phone: List Screen and Details Screen:</b><br />
 <img src="https://github.com/eduardodelito/Cartrack/blob/master/screenshot-phone/Screenshot_20200730-160451_Cartrack.jpg" width="20%" />
 &nbsp;&nbsp;
 <img src="https://github.com/eduardodelito/Cartrack/blob/master/screenshot-phone/Screenshot_20200730-160501_Cartrack.jpg" width="20%" />
 &nbsp;&nbsp;
 <img src="https://github.com/eduardodelito/Cartrack/blob/master/screenshot-phone/Screenshot_20200730-160525_Cartrack.jpg" width="20%" />
 &nbsp;&nbsp;
 <img src="https://github.com/eduardodelito/Cartrack/blob/master/screenshot-phone/Screenshot_20200730-160535_Cartrack.jpg" width="20%" />
 &nbsp;&nbsp;
 <img src="https://github.com/eduardodelito/Cartrack/blob/master/screenshot-phone/Screenshot_20200730-160558_Cartrack.jpg" width="20%" />
 &nbsp;&nbsp;
 <img src="https://github.com/eduardodelito/Cartrack/blob/master/screenshot-phone/Screenshot_20200730-160606_Cartrack.jpg" width="20%" />
 &nbsp;&nbsp;
 <img src="https://github.com/eduardodelito/Cartrack/blob/master/screenshot-phone/Screenshot_20200730-160620_Cartrack.jpg" width="20%" />
  
 <br /><br />
 
 <b>Tablet: List and Details on single screen.</b><br />
 <img src="https://github.com/eduardodelito/Cartrack/blob/master/screeshot-tablet/Screenshot_1596098924.png" width="30%" /> &nbsp;&nbsp;
 <img src="https://github.com/eduardodelito/Cartrack/blob/master/screeshot-tablet/Screenshot_1596098935.png" width="30%" /> &nbsp;&nbsp;
 <img src="https://github.com/eduardodelito/Cartrack/blob/master/screeshot-tablet/Screenshot_1596098939.png" width="30%" /> &nbsp;&nbsp;
 <img src="https://github.com/eduardodelito/Cartrack/blob/master/screeshot-tablet/Screenshot_1596098958.png" width="30%" /> &nbsp;&nbsp;
 <img src="https://github.com/eduardodelito/Cartrack/blob/master/screeshot-tablet/Screenshot_1596099019.png" width="30%" /> &nbsp;&nbsp;
 <img src="https://github.com/eduardodelito/Cartrack/blob/master/screeshot-tablet/Screenshot_1596099043.png" width="30%" /> &nbsp;&nbsp;
 <br /><br />
 
 



