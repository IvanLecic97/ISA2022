import React, { useState, useEffect } from "react";
import storage from "../../firebaseConfig";
import { ref, uploadBytesResumable, getDownloadURL } from "firebase/storage";
import Resizer from "react-image-file-resizer";
import { resolvePath } from "react-router-dom";

function BungalowPage() {
  const [image, setImage] = useState();
  const [url, setImageUrl] = useState("");
  const [imageName, setImageName] = useState(localStorage.getItem("image"));

  const loadImage = async () => {
    const storageRef = ref(storage, `/files/${imageName}`);

    getDownloadURL(storageRef).then((url) => {
      console.log(url);
      setImage(url);
    });

    /*const photo = Resizer.imageFileResizer(
      url,
      120,
      120,
      "PNG",
      100,
      0,
      (uri) => {
        resolvePath(uri);
      },
      "base64"
    );
    setImage(photo); */

    console.log(storageRef);
  };

  const onClick = () => {
    window.open("/updateClient");
  };

  useEffect(() => {
    loadImage();
  }, []);
  return (
    <div>
      <img src={image} />
    </div>
  );
}

export default BungalowPage;
