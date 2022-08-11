// Import the functions you need from the SDKs you need
import { initializeApp } from "firebase/app";
import { getAnalytics } from "firebase/analytics";
import { getStorage } from "firebase/storage";
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
// For Firebase JS SDK v7.20.0 and later, measurementId is optional
const firebaseConfig = {
  apiKey: "AIzaSyB6W99Hd2AwQ3QSEQlbuzHqGVchYHGrP7U",
  authDomain: "reservationsapp-5cf50.firebaseapp.com",
  projectId: "reservationsapp-5cf50",
  storageBucket: "reservationsapp-5cf50.appspot.com",
  messagingSenderId: "998671058124",
  appId: "1:998671058124:web:dc4f2fed2657e342c9ee69",
  measurementId: "G-0ZPRVGYK78",
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);
//const analytics = getAnalytics(app);

const storage = getStorage(app);
export default storage;
