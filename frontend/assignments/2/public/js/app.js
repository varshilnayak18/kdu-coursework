const toggleBtn = document.getElementById("toggler");
const container = document.getElementById("container-2");
const div = document.querySelector(".navigation-section");
let toggle = false;

toggleBtn.addEventListener("click", () => {
  container.style.opacity = toggle ? 1 : 0;
  div.classList.toggle("show");
  toggle = !toggle;
});


const postBtns = document.querySelectorAll(".tweet-btn");
const postsContainer = document.getElementById("posts");
postBtns.forEach((btn) => {
  btn.addEventListener("click", (e) => {
    const profileImgSrc = document.getElementById("profile-img").src;
    const name = document.querySelector(".profile-name").textContent;
    const username = document.querySelector(".profile-username").textContent;
    const time = getTime();
    let caption;
    let fileType;
    let fileSrc;

    const par = e.target.parentElement;
    let postInput;
    let fileInput;
    if (par.classList.contains("post-btn-container")) {
      postInput =
        e.target.parentElement.parentElement.parentElement.querySelector(
          ".post-input"
        );
      fileInput = document.getElementById("file-input");
    } else {
      postInput =
        e.target.parentElement.parentElement.parentElement.parentElement.parentElement.querySelector(
          ".post-input"
        );
      fileInput = document.getElementById("file-input-m");
      header.style.display = "flex";
      topContainer.style.display = "flex";
      postsContainer.style.display = "initial";
      plusBtn.style.display = "initial";
      mobileTweetBox.style.display = "none";
    }
    const file = fileInput.files[0];
    const reader = new FileReader();
    if (file) {
      console.log("here");
      reader.onload = () => {
        fileType = file.type.split("/")[0];
        console.log("filetype", fileType);
        fileSrc = reader.result;
        console.log(fileSrc);
        caption = postInput.value;
        caption = postInput.value;
        if (caption === "") {
          return;
        } else {
          fileInput.value = "";
          document.querySelector(".file-upload-container").style.display =
            "none";
          postInput.value = "";
        }
        tweetBtn[0].style.opacity = 0.5;
        tweetBtn[1].style.opacity = 0.5;
        console.log(fileType, fileSrc);
        addPost(
          profileImgSrc,
          name,
          username,
          time,
          caption,
          fileType,
          fileSrc
        );
      };
      reader.readAsDataURL(file);
    } else {
      caption = postInput.value;
      if (caption === "") {
        return;
      } else {
        postInput.value = "";
      }
      tweetBtn[0].style.opacity = 0.5;
      tweetBtn[1].style.opacity = 0.5;
      addPost(profileImgSrc, name, username, time, caption, null, null);
    }
  });
});


const postInput = document.getElementsByClassName("post-input");
const tweetBtn = document.getElementsByClassName("tweet-btn");
postInput[0].addEventListener("keyup", () => {
  if (postInput[0].value !== "") {
    tweetBtn[0].style.opacity = 1;
  } else {
    tweetBtn[0].style.opacity = 0.5;
  }
});

postInput[1].addEventListener("keyup", () => {
  if (postInput[1].value !== "") {
    tweetBtn[1].style.opacity = 1;
  } else {
    tweetBtn[1].style.opacity = 0.5;
  }
});


const plusBtn = document.getElementById("float-btn");
const backBtn = document.getElementById("back-btn");
const mobileTweetBox = document.querySelector(".mobile-tweet-box");
const header = document.querySelector(".header");
const topContainer = document.querySelector(".top-container");

plusBtn.addEventListener("click", () => {
  header.style.display = "none";
  topContainer.style.display = "none";
  postsContainer.style.display = "none";
  plusBtn.style.display = "none";
  mobileTweetBox.style.display = "initial";
});

backBtn.addEventListener("click", () => {
  header.style.display = "flex";
  topContainer.style.display = "flex";
  postsContainer.style.display = "initial";
  plusBtn.style.display = "flex";
  mobileTweetBox.style.display = "none";
});


document.querySelector(".file-upload").addEventListener("click", () => {
  document.getElementById("file-input").click();
});

document.getElementById("file-input").addEventListener("change", (event) => {
  const file = event.target.files[0];
  const reader = new FileReader();

  reader.onload = () => {
    const fileType = file.type.split("/")[0];
    if (fileType === "image") {
      const imagePreview = document.getElementById("image-preview");
      imagePreview.src = reader.result;
      imagePreview.style.display = "block";
      document.querySelector(".file-upload-container").style.display =
        "initial";
      document.getElementById("video-preview").style.display = "none";
    } else if (fileType === "video") {
      const videoPreview = document.getElementById("video-preview");
      videoPreview.src = reader.result;
      videoPreview.style.display = "block";
      document.querySelector(".file-upload-container").style.display =
        "initial";
      document.getElementById("image-preview").style.display = "none";
    }
  };

  reader.readAsDataURL(file);
});

document.querySelector(".file-upload-m").addEventListener("click", () => {
  document.getElementById("file-input-m").click();
});

document.getElementById("file-input-m").addEventListener("change", (event) => {
  const file = event.target.files[0];
  const reader = new FileReader();

  reader.onload = () => {
    const fileType = file.type.split("/")[0];
    if (fileType === "image") {
      const imagePreview = document.getElementById("image-preview-m");
      imagePreview.src = reader.result;
      imagePreview.style.display = "block";
      document.querySelector(".file-upload-container-m").style.display =
        "initial";
      document.getElementById("video-preview-m").style.display = "none";
    } else if (fileType === "video") {
      const videoPreview = document.getElementById("video-preview-m");
      videoPreview.src = reader.result;
      videoPreview.style.display = "block";
      document.querySelector(".file-upload-container-m").style.display =
        "initial";
      document.getElementById("image-preview-m").style.display = "none";
    }
  };
  reader.readAsDataURL(file);
});


function createPostHTML(
  profileImgSrc,
  name,
  username,
  time,
  caption,
  fileType,
  fileSrc
) {
  // Create div elements
  const profileImgDiv = document.createElement("div");
  profileImgDiv.classList.add("profile-img");

  const img = document.createElement("img");
  img.src = profileImgSrc;
  img.alt = "profile";
  profileImgDiv.appendChild(img);

  const contentDiv = document.createElement("div");
  contentDiv.classList.add("content");

  const postInfoDiv = document.createElement("div");
  postInfoDiv.classList.add("post-info");

  const postProfileInfoDiv = document.createElement("div");
  postProfileInfoDiv.classList.add("post-profile-info");

  const postUserSpan = document.createElement("span");
  postUserSpan.classList.add("post-user");
  postUserSpan.innerHTML = `${name} <span class="post-username">${username} · ${time}</span>`;
  postProfileInfoDiv.appendChild(postUserSpan);

  const postThreeDotOptionDiv = document.createElement("div");
  postThreeDotOptionDiv.classList.add("post-three-dot-option");

  const svgIcon1 = document.createElementNS(
    "http://www.w3.org/2000/svg",
    "svg"
  );
  svgIcon1.setAttribute("class", "logo");
  svgIcon1.setAttribute("style", "fill:#71767a");
  svgIcon1.setAttribute("viewBox", "0 0 24 24");
  svgIcon1.setAttribute("aria-hidden", "true");
  svgIcon1.innerHTML =
    '<g><path d="M3 12c0-1.1.9-2 2-2s2 .9 2 2-.9 2-2 2-2-.9-2-2zm9 2c1.1 0 2-.9 2-2s-.9-2-2-2-2 .9-2 2 .9 2 2 2zm7 0c1.1 0 2-.9 2-2s-.9-2-2-2-2 .9-2 2 .9 2 2 2z"></path></g>';
  postThreeDotOptionDiv.appendChild(svgIcon1);

  postInfoDiv.appendChild(postProfileInfoDiv);
  postInfoDiv.appendChild(postThreeDotOptionDiv);

  const postCaptionDiv = document.createElement("div");
  postCaptionDiv.classList.add("post-caption");

  const words = caption.split(" ");
  words.forEach((word) => {
    if (word.startsWith("#")) {
      const hashtagSpan = document.createElement("span");
      hashtagSpan.classList.add("post-hashtag");
      hashtagSpan.textContent = word + " ";
      postCaptionDiv.appendChild(hashtagSpan);
    } else {
      postCaptionDiv.appendChild(document.createTextNode(word + " "));
    }
  });

  const fileUploadedContainer = document.createElement("div");
  fileUploadedContainer.classList.add("file-uploaded");
  let fileContent = null;
  if (fileType && fileSrc) {
    if (fileType === "image") {
      fileContent = document.createElement("img");
      fileContent.src = fileSrc;
      fileContent.alt = "Uploaded Image";
    } else if (fileType === "video") {
      fileContent = document.createElement("video");
      fileContent.src = fileSrc;
      fileContent.controls = true;
      fileContent.autoplay = true;
      fileContent.muted = true;
    }
    fileUploadedContainer.appendChild(fileContent);
  }
  const postIconsDiv = document.createElement("div");
  postIconsDiv.classList.add("post-icons");

  // Create icon divs individually
  // Create icon 1
  const iconDiv1 = document.createElement("div");
  iconDiv1.classList.add("icon");

  const logoContainerDiv1 = document.createElement("div");
  logoContainerDiv1.classList.add("logo-container");

  const svg1 = document.createElementNS("http://www.w3.org/2000/svg", "svg");
  svg1.setAttribute("class", "logo");
  svg1.setAttribute("style", "fill:#71767a");
  svg1.setAttribute("viewBox", "0 0 24 24");
  svg1.setAttribute("aria-hidden", "true");
  svg1.innerHTML =
    '<path d="M1.751 10c0-4.42 3.584-8 8.005-8h4.366c4.49 0 8.129 3.64 8.129 8.13 0 2.96-1.607 5.68-4.196 7.11l-8.054 4.46v-3.69h-.067c-4.49.1-8.183-3.51-8.183-8.01zm8.005-6c-3.317 0-6.005 2.69-6.005 6 0 3.37 2.77 6.08 6.138 6.01l.351-.01h1.761v2.3l5.087-2.81c1.951-1.08 3.163-3.13 3.163-5.36 0-3.39-2.744-6.13-6.129-6.13H9.756z"></path>';

  const counterDiv1 = document.createElement("div");
  counterDiv1.classList.add("counter");

  logoContainerDiv1.appendChild(svg1);
  iconDiv1.appendChild(logoContainerDiv1);
  iconDiv1.appendChild(counterDiv1);

  // Create icon 2
  const iconDiv2 = document.createElement("div");
  iconDiv2.classList.add("icon");

  const logoContainerDiv2 = document.createElement("div");
  logoContainerDiv2.classList.add("logo-container");

  const svg2 = document.createElementNS("http://www.w3.org/2000/svg", "svg");
  svg2.setAttribute("class", "logo");
  svg2.setAttribute("style", "fill:#71767a");
  svg2.setAttribute("viewBox", "0 0 24 24");
  svg2.setAttribute("aria-hidden", "true");
  svg2.innerHTML =
    '<path d="M4.5 3.88l4.432 4.14-1.364 1.46L5.5 7.55V16c0 1.1.896 2 2 2H13v2H7.5c-2.209 0-4-1.79-4-4V7.55L1.432 9.48.068 8.02 4.5 3.88zM16.5 6H11V4h5.5c2.209 0 4 1.79 4 4v8.45l2.068-1.93 1.364 1.46-4.432 4.14-4.432-4.14 1.364-1.46 2.068 1.93V8c0-1.1-.896-2-2-2z"></path>';

  const counterDiv2 = document.createElement("div");
  counterDiv2.classList.add("counter");

  logoContainerDiv2.appendChild(svg2);
  iconDiv2.appendChild(logoContainerDiv2);
  iconDiv2.appendChild(counterDiv2);

  // Create icon 3
  const iconDiv3 = document.createElement("div");
  iconDiv3.classList.add("icon");

  const logoContainerDiv3 = document.createElement("div");
  logoContainerDiv3.classList.add("logo-container");

  const svg3 = document.createElementNS("http://www.w3.org/2000/svg", "svg");
  svg3.setAttribute("class", "logo like-btn");
  svg3.setAttribute("style", "fill:#71767a");
  svg3.setAttribute("viewBox", "0 0 24 24");
  svg3.setAttribute("aria-hidden", "true");
  svg3.innerHTML =
    '<path d="M16.697 5.5c-1.222-.06-2.679.51-3.89 2.16l-.805 1.09-.806-1.09C9.984 6.01 8.526 5.44 7.304 5.5c-1.243.07-2.349.78-2.91 1.91-.552 1.12-.633 2.78.479 4.82 1.074 1.97 3.257 4.27 7.129 6.61 3.87-2.34 6.052-4.64 7.126-6.61 1.111-2.04 1.03-3.7.477-4.82-.561-1.13-1.666-1.84-2.908-1.91zm4.187 7.69c-1.351 2.48-4.001 5.12-8.379 7.67l-.503.3-.504-.3c-4.379-2.55-7.029-5.19-8.382-7.67-1.36-2.5-1.41-4.86-.514-6.67.887-1.79 2.647-2.91 4.601-3.01 1.651-.09 3.368.56 4.798 2.01 1.429-1.45 3.146-2.1 4.796-2.01 1.954.1 3.714 1.22 4.601 3.01.896 1.81.846 4.17-.514 6.67z"></path>';
  const counterDiv3 = document.createElement("div");
  counterDiv3.classList.add("counter");

  svg3.addEventListener("click", () => {
    const path = svg3.querySelector("path");
    if (
      path.getAttribute("d") ===
      "M16.697 5.5c-1.222-.06-2.679.51-3.89 2.16l-.805 1.09-.806-1.09C9.984 6.01 8.526 5.44 7.304 5.5c-1.243.07-2.349.78-2.91 1.91-.552 1.12-.633 2.78.479 4.82 1.074 1.97 3.257 4.27 7.129 6.61 3.87-2.34 6.052-4.64 7.126-6.61 1.111-2.04 1.03-3.7.477-4.82-.561-1.13-1.666-1.84-2.908-1.91zm4.187 7.69c-1.351 2.48-4.001 5.12-8.379 7.67l-.503.3-.504-.3c-4.379-2.55-7.029-5.19-8.382-7.67-1.36-2.5-1.41-4.86-.514-6.67.887-1.79 2.647-2.91 4.601-3.01 1.651-.09 3.368.56 4.798 2.01 1.429-1.45 3.146-2.1 4.796-2.01 1.954.1 3.714 1.22 4.601 3.01.896 1.81.846 4.17-.514 6.67z"
    ) {
      path.setAttribute(
        "d",
        "M20.884 13.19c-1.351 2.48-4.001 5.12-8.379 7.67l-.503.3-.504-.3c-4.379-2.55-7.029-5.19-8.382-7.67-1.36-2.5-1.41-4.86-.514-6.67.887-1.79 2.647-2.91 4.601-3.01 1.651-.09 3.368.56 4.798 2.01 1.429-1.45 3.146-2.1 4.796-2.01 1.954.1 3.714 1.22 4.601 3.01.896 1.81.846 4.17-.514 6.67z"
      );
      svg3.style.fill = "#f91880";
      counterDiv3.textContent = "1";
      counterDiv3.style.fontSize = "13px";
      counterDiv3.style.marginLeft = "5px";
      counterDiv3.style.display = "flex";
      counterDiv3.style.alignItems = "center";
    } else {
      path.setAttribute(
        "d",
        "M16.697 5.5c-1.222-.06-2.679.51-3.89 2.16l-.805 1.09-.806-1.09C9.984 6.01 8.526 5.44 7.304 5.5c-1.243.07-2.349.78-2.91 1.91-.552 1.12-.633 2.78.479 4.82 1.074 1.97 3.257 4.27 7.129 6.61 3.87-2.34 6.052-4.64 7.126-6.61 1.111-2.04 1.03-3.7.477-4.82-.561-1.13-1.666-1.84-2.908-1.91zm4.187 7.69c-1.351 2.48-4.001 5.12-8.379 7.67l-.503.3-.504-.3c-4.379-2.55-7.029-5.19-8.382-7.67-1.36-2.5-1.41-4.86-.514-6.67.887-1.79 2.647-2.91 4.601-3.01 1.651-.09 3.368.56 4.798 2.01 1.429-1.45 3.146-2.1 4.796-2.01 1.954.1 3.714 1.22 4.601 3.01.896 1.81.846 4.17-.514 6.67z"
      );
      svg3.style.fill = "#71767a";
      counterDiv3.textContent = "";
    }
  });

  logoContainerDiv3.appendChild(svg3);
  iconDiv3.appendChild(logoContainerDiv3);
  iconDiv3.appendChild(counterDiv3);

  // Create icon 4
  const iconDiv4 = document.createElement("div");
  iconDiv4.classList.add("icon");

  const logoContainerDiv4 = document.createElement("div");
  logoContainerDiv4.classList.add("logo-container");

  const svg4 = document.createElementNS("http://www.w3.org/2000/svg", "svg");
  svg4.setAttribute("class", "logo");
  svg4.setAttribute("style", "fill:#71767a");
  svg4.setAttribute("viewBox", "0 0 24 24");
  svg4.setAttribute("aria-hidden", "true");
  svg4.innerHTML =
    '<path d="M8.75 21V3h2v18h-2zM18 21V8.5h2V21h-2zM4 21l.004-10h2L6 21H4zm9.248 0v-7h2v7h-2z"></path>';

  const counterDiv4 = document.createElement("div");
  counterDiv4.classList.add("counter");

  logoContainerDiv4.appendChild(svg4);
  iconDiv4.appendChild(logoContainerDiv4);
  iconDiv4.appendChild(counterDiv4);

  // Create icon 5
  const iconDiv5 = document.createElement("div");
  iconDiv5.classList.add("icon");
  iconDiv5.classList.add("last-icon");

  const logoContainerDiv5 = document.createElement("div");
  logoContainerDiv5.classList.add("logo-container");

  const svg5 = document.createElementNS("http://www.w3.org/2000/svg", "svg");
  svg5.setAttribute("class", "logo");
  svg5.setAttribute("style", "fill:#71767a");
  svg5.setAttribute("viewBox", "0 0 24 24");
  svg5.setAttribute("aria-hidden", "true");
  svg5.innerHTML =
    '<path d="M4 4.5C4 3.12 5.119 2 6.5 2h11C18.881 2 20 3.12 20 4.5v18.44l-8-5.71-8 5.71V4.5zM6.5 4c-.276 0-.5.22-.5.5v14.56l6-4.29 6 4.29V4.5c0-.28-.224-.5-.5-.5h-11z"></path>';

  const counterDiv5 = document.createElement("div");
  counterDiv5.classList.add("counter");

  logoContainerDiv5.appendChild(svg5);
  iconDiv5.appendChild(logoContainerDiv5);
  iconDiv5.appendChild(counterDiv5);

  // Create icon 6
  const iconDiv6 = document.createElement("div");
  iconDiv6.classList.add("icon");
  iconDiv6.classList.add("share-icon");

  const logoContainerDiv6 = document.createElement("div");
  logoContainerDiv6.classList.add("logo-container");

  const svg6 = document.createElementNS("http://www.w3.org/2000/svg", "svg");
  svg6.setAttribute("class", "logo");
  svg6.setAttribute("style", "fill:#71767a");
  svg6.setAttribute("viewBox", "0 0 24 24");
  svg6.setAttribute("aria-hidden", "true");
  svg6.innerHTML =
    '<path d="M12 2.59l5.7 5.7-1.41 1.42L13 6.41V16h-2V6.41l-3.3 3.3-1.41-1.42L12 2.59zM21 15l-.02 3.51c0 1.38-1.12 2.49-2.5 2.49H5.5C4.11 21 3 19.88 3 18.5V15h2v3.5c0 .28.22.5.5.5h12.98c.28 0 .5-.22.5-.5L19 15h2z"></path>';

  const counterDiv6 = document.createElement("div");
  counterDiv6.classList.add("counter");

  logoContainerDiv6.appendChild(svg6);
  iconDiv6.appendChild(logoContainerDiv6);
  iconDiv6.appendChild(counterDiv6);

  // Append icons to postIconsDiv
  postIconsDiv.appendChild(iconDiv1);
  postIconsDiv.appendChild(iconDiv2);
  postIconsDiv.appendChild(iconDiv3);
  postIconsDiv.appendChild(iconDiv4);
  postIconsDiv.appendChild(iconDiv5);
  postIconsDiv.appendChild(iconDiv6);

  const postDiv = document.createElement("div");
  postDiv.classList.add("post");
  postDiv.appendChild(profileImgDiv);
  contentDiv.appendChild(postInfoDiv);
  contentDiv.appendChild(postCaptionDiv);
  contentDiv.appendChild(fileUploadedContainer);
  contentDiv.appendChild(postIconsDiv);
  postDiv.appendChild(contentDiv);

  return postDiv;
}

function createPost(post) {
  const { profileImgSrc, name, username, time, caption, fileType, fileSrc } =
  post;
  const postDiv = createPostHTML(
    profileImgSrc,
    name,
    username,
    time,
    caption,
    fileType,
    fileSrc
    );
    postsContainer.append(postDiv);
  }

  
function getTime() {
    const currentDate = new Date();
  const hours = currentDate.getHours();
  let minutes = currentDate.getMinutes();
  if (minutes < 10) {
    minutes = "0" + minutes;
  }
  return `${hours}:${minutes}`;
}


const addPost = async (
  profileImgSrc,
  name,
  username,
  time,
  caption,
  fileType,
  fileSrc
) => {
  try {
    const response = await fetch("/api/posts", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        profileImgSrc,
        name,
        username,
        time,
        caption,
        fileType,
        fileSrc,
      }),
    });
    if (response.ok) {
      console.log("Post added successfully");
      setTimeout(() => {
        location.reload();
      }, 1000);
    } else {
      console.error("Add Post failed");
    }
  } catch (error) {
    console.error("Error:", error);
  }
};


const pageSize = 5;
let currentPage = 1;
const renderPage = async (username, page = 1) => {
  try {
    const userResponse = await fetch(`/api/user/${username}`);
    const userData = await userResponse.json();

    document.getElementById("profile-img").src = userData.profile_url;
    document.getElementById("profile-img-m").src = userData.profile_url;
    document.getElementById("tweet-box-img").src = userData.profile_url;
    document.getElementById("tweet-box-img-m").src = userData.profile_url;
    document.getElementById("header-img").src = userData.profile_url;
    document.querySelector(".profile-name").textContent = userData.name;
    document.querySelector(".profile-username").textContent =
      "@" + userData.user_name;
    document.querySelector(".name").textContent = userData.name;
    document.querySelector(".username").textContent = "@" + userData.user_name;
    document.getElementById("msg-user-profile-img").src = userData.profile_url;

    const response = await fetch(
      `/api/posts?page=${page}&pageSize=${pageSize}`
    );
    if (response.ok) {
      const data = await response.json();
      postsContainer.innerHTML = "";

      data.forEach((post) => createPost(post));
    } else {
      console.error("Error fetching posts:", response.statusText);
    }
  } catch (error) {
    console.error("Error fetching posts:", error);
  }
};

const fetchPosts = async (page) => {
  try {
    const response = await fetch(
      `/api/posts?page=${page}&pageSize=${pageSize}`
    );
    if (response.ok) {
      const data = await response.json();
      data.forEach((post) => createPost(post));
    } else {
      console.error("Error fetching posts:", response.statusText);
    }
  } catch (error) {
    console.error("Error fetching posts:", error);
  }
};


let isLoadingNextPage = false;
const isAtBottom = () => {
  const container2 = document.querySelector(".container-2");
  return (
    container2.scrollTop + container2.clientHeight >=
    container2.scrollHeight - 50
  );
};

document.querySelector(".container-2").addEventListener("scroll", async () => {
  if (isAtBottom() && !isLoadingNextPage) {
    isLoadingNextPage = true;
    setTimeout(async () => {
      currentPage++;
      await fetchPosts(currentPage);
      isLoadingNextPage = false;
    }, 1000);
  }
});


const params = new URLSearchParams(window.location.search);
const username = params.get("username");
renderPage(username, currentPage);


const homeBtn = document.getElementById("home-btn");
const msgBtn = document.getElementById("msg-btn");
const homeDiv = document.querySelector(".container-2");
const msgDiv = document.querySelector(".container-3");
const homeBtnM = document.getElementById("home-btn-m");
const msgBtnM = document.getElementById("msg-btn-m");
const chatDiv = document.querySelector(".chat-container");
const homeLogo = document.getElementById("home-logo");
const msgLogo = document.getElementById("msg-logo");
const homeLogoM = document.getElementById("home-logo-m");
const msgLogoM = document.getElementById("msg-logo-m");
homeBtn.addEventListener("click", () => {
  homeDiv.style.display = "initial";
  msgDiv.style.display = "none";
  chatDiv.style.display = "none";
  document.getElementById("nav-home").style.fontWeight = "bold";
  document.getElementById("nav-msg").style.fontWeight = "initial";
  homeLogo.setAttribute(
    "d",
    "M21.591 7.146L12.52 1.157c-.316-.21-.724-.21-1.04 0l-9.071 5.99c-.26.173-.409.456-.409.757v13.183c0 .502.418.913.929.913H9.14c.51 0 .929-.41.929-.913v-7.075h3.909v7.075c0 .502.417.913.928.913h6.165c.511 0 .929-.41.929-.913V7.904c0-.301-.158-.584-.408-.758z"
  );
  homeLogo.style.fill = "#fff";
  msgLogo.setAttribute(
    "d",
    "M1.998 5.5c0-1.381 1.119-2.5 2.5-2.5h15c1.381 0 2.5 1.119 2.5 2.5v13c0 1.381-1.119 2.5-2.5 2.5h-15c-1.381 0-2.5-1.119-2.5-2.5v-13zm2.5-.5c-.276 0-.5.224-.5.5v2.764l8 3.638 8-3.636V5.5c0-.276-.224-.5-.5-.5h-15zm15.5 5.463l-8 3.636-8-3.638V18.5c0 .276.224.5.5.5h15c.276 0 .5-.224.5-.5v-8.037z"
  );
  msgLogo.style.fill = "#fff";
});
msgBtn.addEventListener("click", () => {
  homeDiv.style.display = "none";
  msgDiv.style.display = "initial";
  chatDiv.style.display = "initial";
  document.getElementById("nav-home").style.fontWeight = "initial";
  document.getElementById("nav-msg").style.fontWeight = "bold";
  homeLogo.setAttribute(
    "d",
    "M21.591 7.146L12.52 1.157c-.316-.21-.724-.21-1.04 0l-9.071 5.99c-.26.173-.409.456-.409.757v13.183c0 .502.418.913.929.913h6.638c.511 0 .929-.41.929-.913v-7.075h3.008v7.075c0 .502.418.913.929.913h6.639c.51 0 .928-.41.928-.913V7.904c0-.301-.158-.584-.408-.758zM20 20l-4.5.01.011-7.097c0-.502-.418-.913-.928-.913H9.44c-.511 0-.929.41-.929.913L8.5 20H4V8.773l8.011-5.342L20 8.764z"
  );
  homeLogo.style.fill = "#fff";
  msgLogo.setAttribute(
    "d",
    "M1.998 4.499c0-.828.671-1.499 1.5-1.499h17c.828 0 1.5.671 1.5 1.499v2.858l-10 4.545-10-4.547V4.499zm0 5.053V19.5c0 .828.671 1.5 1.5 1.5h17c.828 0 1.5-.672 1.5-1.5V9.554l-10 4.545-10-4.547z"
  );
  msgLogo.style.fill = "#fff";
});
homeBtnM.addEventListener("click", () => {
  homeDiv.style.display = "initial";
  msgDiv.style.display = "none";
  homeLogoM.setAttribute(
    "d",
    "M21.591 7.146L12.52 1.157c-.316-.21-.724-.21-1.04 0l-9.071 5.99c-.26.173-.409.456-.409.757v13.183c0 .502.418.913.929.913H9.14c.51 0 .929-.41.929-.913v-7.075h3.909v7.075c0 .502.417.913.928.913h6.165c.511 0 .929-.41.929-.913V7.904c0-.301-.158-.584-.408-.758z"
  );
  homeLogoM.style.fill = "#fff";
  msgLogoM.setAttribute(
    "d",
    "M1.998 5.5c0-1.381 1.119-2.5 2.5-2.5h15c1.381 0 2.5 1.119 2.5 2.5v13c0 1.381-1.119 2.5-2.5 2.5h-15c-1.381 0-2.5-1.119-2.5-2.5v-13zm2.5-.5c-.276 0-.5.224-.5.5v2.764l8 3.638 8-3.636V5.5c0-.276-.224-.5-.5-.5h-15zm15.5 5.463l-8 3.636-8-3.638V18.5c0 .276.224.5.5.5h15c.276 0 .5-.224.5-.5v-8.037z"
  );
  msgLogoM.style.fill = "#fff";
});
msgBtnM.addEventListener("click", () => {
  homeDiv.style.display = "none";
  msgDiv.style.display = "initial";
  homeLogoM.setAttribute(
    "d",
    "M21.591 7.146L12.52 1.157c-.316-.21-.724-.21-1.04 0l-9.071 5.99c-.26.173-.409.456-.409.757v13.183c0 .502.418.913.929.913h6.638c.511 0 .929-.41.929-.913v-7.075h3.008v7.075c0 .502.418.913.929.913h6.639c.51 0 .928-.41.928-.913V7.904c0-.301-.158-.584-.408-.758zM20 20l-4.5.01.011-7.097c0-.502-.418-.913-.928-.913H9.44c-.511 0-.929.41-.929.913L8.5 20H4V8.773l8.011-5.342L20 8.764z"
  );
  homeLogoM.style.fill = "#fff";
  msgLogoM.setAttribute(
    "d",
    "M1.998 4.499c0-.828.671-1.499 1.5-1.499h17c.828 0 1.5.671 1.5 1.499v2.858l-10 4.545-10-4.547V4.499zm0 5.053V19.5c0 .828.671 1.5 1.5 1.5h17c.828 0 1.5-.672 1.5-1.5V9.554l-10 4.545-10-4.547z"
  );
  msgLogoM.style.fill = "#fff";
});


const socket = io();

const connectedUsersList = document.querySelector(".msg-users");

socket.emit("join-server", username);

socket.on("new-user", (connectedUsers) => {
  connectedUsersList.innerHTML = "";
  connectedUsers.forEach((user) => {
    createMsgUser(user);
  });
});

async function createMsgUser(user) {
  const userResponse = await fetch(`/api/user/${user.username}`);
  const userData = await userResponse.json();
  const msgUserDiv = document.createElement("div");
  msgUserDiv.classList.add("msg-user");

  const msgUserProfileDiv = document.createElement("div");
  msgUserProfileDiv.classList.add("msg-user-profile");
  const profileImg = document.createElement("img");
  profileImg.src = userData.profile_url;
  profileImg.alt = "profile";
  msgUserProfileDiv.appendChild(profileImg);
  const msgUserInfoDiv = document.createElement("div");
  msgUserInfoDiv.classList.add("msg-user-info");
  const userNameSpan = document.createElement("span");
  userNameSpan.classList.add("msg-user-name");
  userNameSpan.textContent = userData.name + " ";
  const userUsernameSpan = document.createElement("span");
  userUsernameSpan.classList.add("msg-user-username");
  userUsernameSpan.textContent = "@" + userData.user_name;
  msgUserInfoDiv.appendChild(userNameSpan);
  msgUserInfoDiv.appendChild(userUsernameSpan);

  msgUserDiv.appendChild(msgUserProfileDiv);
  msgUserDiv.appendChild(msgUserInfoDiv);
  connectedUsersList.append(msgUserDiv);

  msgUserDiv.addEventListener("click", (e) => {
    if (window.innerWidth < 500) {
      document.querySelector(".container-3").style.display = "none";
      document.querySelector(".chat-container").style.display = "initial";
      const backBtn = document.querySelector(".chat-back-btn");
      document.querySelector(".bottom-navbar").style.display = "none";
      backBtn.style.display = "initial";
      backBtn.addEventListener("click", () => {
        document.querySelector(".container-3").style.display = "initial";
        document.querySelector(".chat-container").style.display = "none";
        backBtn.style.display = "none";
        document.querySelector(".bottom-navbar").style.display = "flex";
      });
    }
    const receiverName = msgUserDiv.querySelector(".msg-user-name").textContent;
    let receiverUsername =
      msgUserDiv.querySelector(".msg-user-username").textContent;
    receiverUsername = receiverUsername.replace("@", "");
    document.querySelector(".chat-user-name").textContent = receiverName;
    const messagesDiv = document.querySelector(".messages");
    messagesDiv.innerHTML = "";
    const sendBtn = document.querySelector(".chat-send-btn");
    sendBtn.addEventListener("click", () => {
      const msg = document.getElementById("chat-msg").value;
      if (msg !== "") {
        createMsg(msg, false);
        if (username !== receiverUsername) {
          const name = document.querySelector(".profile-name").textContent;
          socket.emit("send-message", msg, name, receiverUsername);
        }
      }
      document.getElementById("chat-msg").value = "";
    });
  });
  if (document.querySelector(".chat-user-name").textContent.trim() === "" && window.innerWidth > 500) {
    document.querySelector(".chat-user-name").textContent = document.querySelector(".msg-user-name").textContent;
  }
}

socket.on("new-message", (messageObject) => {
  if (
    messageObject.sender.trim() ===
    document.querySelector(".chat-user-name").textContent.trim()
  ) {
    createMsg(messageObject.msg, true);
  }  
});

function createMsg(msg, receive) {
  const messagesDiv = document.querySelector(".messages");
  const messageDiv = document.createElement("div");
  const timeDiv = document.createElement('div');
  if (receive) {
    messageDiv.classList.add("message-receive");
    timeDiv.classList.add('message-receive-time');
  } else {
    messageDiv.classList.add("message-send");
    timeDiv.classList.add('message-send-time');
  }
  messageDiv.textContent = msg;
  messagesDiv.append(messageDiv);
  timeDiv.textContent = getMsgTime();
  messagesDiv.append(timeDiv);
}

function getMsgTime() {
  const now = new Date();
  const options = { hour: 'numeric', minute: 'numeric', hour12: true };
  return now.toLocaleString('en-US', options);
}
