// /static/script/script.js
console.log("Script loaded ")
document.addEventListener("DOMContentLoaded", function () {
  const button = document.getElementById("theme_change_button");
  const html = document.documentElement;

  // Load from localStorage
  const savedTheme = localStorage.getItem("theme");
  if (savedTheme === "dark") {
      html.classList.add("dark");
  } else {
      html.classList.remove("dark");
  }

  // Update button label
  button.querySelector("span").textContent = html.classList.contains("dark") ? "Dark" : "Light";

  // Toggle button
  button.addEventListener("click", () => {
      html.classList.toggle("dark");
      const isDark = html.classList.contains("dark");
      localStorage.setItem("theme", isDark ? "dark" : "light");
      button.querySelector("span").textContent = isDark ? "Dark" : "Light";
  });
});
