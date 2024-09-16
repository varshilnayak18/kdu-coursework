// Define interfaces for Recipe and RawRecipe
interface Recipe {
    image: string;
    name: string;
    rating: number;
    cuisine: string;
    ingredients: string[];
    difficulty: string;
    timeTaken: number;
    caloriesPerServing: number;
}

interface RawRecipe {
    id: number;
    name: string;
    ingredients: string[];
    instructions: string[];
    prepTimeMinutes: number;
    cookTimeMinutes: number;
    servings: number;
    difficulty: string;
    cuisine: string;
    caloriesPerServing: number;
    tags: string[];
    userId: number;
    image: string;
    rating: number;
    reviewCount: number;
    mealType: string[];
}

// Function to fetch recipes from the API
async function fetchRecipesFromAPI(url: string): Promise<RawRecipe[]> {
    const response = await fetch(url);
    const data = await response.json();
    return data.recipes as RawRecipe[];
}


// Function to print recipes on the page
async function printAllRecipes(url: string) {
    try {
        const recipeContainer = document.getElementById('main') as HTMLDivElement;
        
        const response = await fetchRecipesFromAPI(url);
        recipeContainer.innerHTML = ''; 
        response.forEach((rawRecipe) => {
            const recipe: Recipe = {
                image: rawRecipe.image,
                name: rawRecipe.name,
                rating: rawRecipe.rating,
                cuisine: rawRecipe.cuisine,
                ingredients: rawRecipe.ingredients,
                difficulty: rawRecipe.difficulty,
                timeTaken: rawRecipe.cookTimeMinutes + rawRecipe.prepTimeMinutes,
                caloriesPerServing: rawRecipe.caloriesPerServing
            };
            const recipeElement = createRecipeElement(recipe);
            recipeContainer.appendChild(recipeElement);
        });
    } catch (error) {
        console.error('Error fetching or processing recipes:', error);
    }
}


async function searchRecipes(query: string){
    printAllRecipes(`https://dummyjson.com/recipes/search?q=${query}`);
}

// Function to handle search
function handleSearch() {
    const searchInput = document.getElementById('search') as HTMLInputElement;
    const query = searchInput.value;
    if (query) {
        searchRecipes(query);
    }
}

// Event listener for search input
document.getElementById('search')?.addEventListener('input', handleSearch);

// Event listener for search button
document.getElementById('btn')?.addEventListener('click', handleSearch);

// Initial display of recipes when the DOM content is loaded
document.addEventListener('DOMContentLoaded', () => {
    printAllRecipes('https://dummyjson.com/recipes');
});

// Function to create recipe HTML elements
function createRecipeElement(recipe: Recipe): HTMLElement {
    const recipeDiv = document.createElement('div');
    recipeDiv.classList.add('recipe');

    const ratingDiv = document.createElement('div');
    ratingDiv.classList.add('ratings');
    ratingDiv.textContent = recipe.rating.toString();
    recipeDiv.appendChild(ratingDiv);
    // Info section
    const infoDiv = document.createElement('div');
    infoDiv.classList.add('info');

    const imgDiv = document.createElement('div');
    imgDiv.classList.add('img');
    const img = document.createElement('img');
    img.src = recipe.image;
    img.alt = 'img';
    imgDiv.appendChild(img);
    infoDiv.appendChild(imgDiv);

    const recipeNameDiv = document.createElement('div');
    recipeNameDiv.classList.add('recipe-name');
    const recipeNameH1 = document.createElement('h1');
    recipeNameH1.textContent = recipe.name;
    recipeNameDiv.appendChild(recipeNameH1);
    infoDiv.appendChild(recipeNameDiv);

    recipeDiv.appendChild(infoDiv);

    // Ingredients section
    const ingredientsDiv = document.createElement('div');
    ingredientsDiv.classList.add('ingredients');
    const ingredientsH2 = document.createElement('h2');
    ingredientsH2.textContent = 'Ingredients';
    ingredientsDiv.appendChild(ingredientsH2);
    recipe.ingredients.forEach(ingredient => {
        const ingredientP = document.createElement('p');
        ingredientP.classList.add('ingredient');
        ingredientP.textContent = ingredient;
        ingredientsDiv.appendChild(ingredientP);
    });
    recipeDiv.appendChild(ingredientsDiv);

    // Additional info section
    const addInfoDiv = document.createElement('div');
    addInfoDiv.classList.add('add-info');

    const cuisineDiv = document.createElement('div');
    cuisineDiv.classList.add('cuisine');
    const cuisineH2 = document.createElement('h2');
    cuisineH2.textContent = 'Cuisine';
    const cuisineText = document.createTextNode(recipe.cuisine);
    cuisineDiv.appendChild(cuisineH2);
    cuisineDiv.appendChild(cuisineText);
    addInfoDiv.appendChild(cuisineDiv);

    const difficultyDiv = document.createElement('div');
    difficultyDiv.classList.add('difficulty');
    const difficultyH2 = document.createElement('h2');
    difficultyH2.textContent = 'Difficulty';
    const difficultyText = document.createTextNode(recipe.difficulty);
    difficultyDiv.appendChild(difficultyH2);
    difficultyDiv.appendChild(difficultyText);
    addInfoDiv.appendChild(difficultyDiv);

    const timeDiv = document.createElement('div');
    timeDiv.classList.add('time');
    const timeH2 = document.createElement('h2');
    timeH2.textContent = 'Time taken';
    const timeText = document.createTextNode(recipe.timeTaken.toString());
    timeDiv.appendChild(timeH2);
    timeDiv.appendChild(timeText);
    addInfoDiv.appendChild(timeDiv);

    const caloriesDiv = document.createElement('div');
    caloriesDiv.classList.add('calories');
    const caloriesH2 = document.createElement('h2');
    caloriesH2.textContent = 'Calories';
    const caloriesText = document.createTextNode(recipe.caloriesPerServing.toString());
    caloriesDiv.appendChild(caloriesH2);
    caloriesDiv.appendChild(caloriesText);
    addInfoDiv.appendChild(caloriesDiv);

    recipeDiv.appendChild(addInfoDiv);

    return recipeDiv;
}