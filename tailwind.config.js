/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["./src/main/resources/templates/**/*.{html,js}"],
  safelist: [
    'bg-sky-500',
    'hover:bg-sky-600',
    'dark:hover:bg-sky-700',
    'text-white',
    'text-gray-900',
    'rounded-lg',
    'p-2.5',
    'w-36'
  ],
  darkMode: 'class',
  theme: {
    extend: {},
  },
  plugins: [],
};
