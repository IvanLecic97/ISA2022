import React, { useState, useEffect } from "react";
import ImageUploading from "react-images-uploading";
import FileSaver, { saveAs } from "file-saver";
import storage from "../../firebaseConfig";
import { ref, uploadBytesResumable, getDownloadURL } from "firebase/storage";

function DiscountedPage() {
  const [loadedEntities, setLoadedEntities] = useState([]);
  const [loading, setLoading] = useState(true);
  const [url, setUrl] = useState(
    "http://localhost:8081/api/discount/getDiscountedEntities"
  );

  const [images, setImages] = React.useState(null);
  const [percent, setPercent] = useState(0);

  const onChangeFile = (event) => {
    setImages(event.target.files[0]);
  };

  const onShow = (event) => {
    event.preventDefault();
    console.log(images);
    //FileSaver.saveAs(images, images.name);
    const storageRef = ref(storage, `/files/${images.name}`);
    const uploadTask = uploadBytesResumable(storageRef, images);
    uploadTask.on(
      "state_changed",
      (snapshot) => {
        const percent = Math.round(
          (snapshot.bytesTransferred / snapshot.totalBytes) * 100
        );

        // update progress
        setPercent(percent);
      },
      (err) => console.log(err),
      () => {
        // download url
        getDownloadURL(uploadTask.snapshot.ref).then((url) => {
          console.log(url);
        });
      }
    );
  };

  const loadEntities = async () => {
    try {
      const response = await fetch(url);
      const json = await response.json();
      console.log(json);
      setLoadedEntities(json);
    } catch (error) {
      console.log("error", error);
    }
    setLoading(false);
  };

  useEffect(() => {
    loadEntities();
  }, []);

  return (
    <>
      <div>
        <input className="uploader" type="file" onChange={onChangeFile} />
        <button onClick={onShow}>Upload</button>
        <p>{percent} "% done"</p>
        {loading ? (
          <h4>Loading...</h4>
        ) : (
          loadedEntities.map((value) => (
            <ul className="discountedEntity" key={value.attractionId}>
              <li>Attraction name : {value.attractionName}</li>
              <li>Old price : {value.oldPrice}</li>
              <li>New price : {value.newPrice}</li>
              <li>Owner username : {value.username}</li>
            </ul>
          ))
        )}
      </div>
    </>
  );
}

export default DiscountedPage;
