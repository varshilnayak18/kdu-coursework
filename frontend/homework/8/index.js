var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
var __generator = (this && this.__generator) || function (thisArg, body) {
    var _ = { label: 0, sent: function() { if (t[0] & 1) throw t[1]; return t[1]; }, trys: [], ops: [] }, f, y, t, g;
    return g = { next: verb(0), "throw": verb(1), "return": verb(2) }, typeof Symbol === "function" && (g[Symbol.iterator] = function() { return this; }), g;
    function verb(n) { return function (v) { return step([n, v]); }; }
    function step(op) {
        if (f) throw new TypeError("Generator is already executing.");
        while (g && (g = 0, op[0] && (_ = 0)), _) try {
            if (f = 1, y && (t = op[0] & 2 ? y["return"] : op[0] ? y["throw"] || ((t = y["return"]) && t.call(y), 0) : y.next) && !(t = t.call(y, op[1])).done) return t;
            if (y = 0, t) op = [op[0] & 2, t.value];
            switch (op[0]) {
                case 0: case 1: t = op; break;
                case 4: _.label++; return { value: op[1], done: false };
                case 5: _.label++; y = op[1]; op = [0]; continue;
                case 7: op = _.ops.pop(); _.trys.pop(); continue;
                default:
                    if (!(t = _.trys, t = t.length > 0 && t[t.length - 1]) && (op[0] === 6 || op[0] === 2)) { _ = 0; continue; }
                    if (op[0] === 3 && (!t || (op[1] > t[0] && op[1] < t[3]))) { _.label = op[1]; break; }
                    if (op[0] === 6 && _.label < t[1]) { _.label = t[1]; t = op; break; }
                    if (t && _.label < t[2]) { _.label = t[2]; _.ops.push(op); break; }
                    if (t[2]) _.ops.pop();
                    _.trys.pop(); continue;
            }
            op = body.call(thisArg, _);
        } catch (e) { op = [6, e]; y = 0; } finally { f = t = 0; }
        if (op[0] & 5) throw op[1]; return { value: op[0] ? op[1] : void 0, done: true };
    }
};
var _a, _b;
// Function to fetch recipes from the API
function fetchRecipesFromAPI(url) {
    return __awaiter(this, void 0, void 0, function () {
        var response, data;
        return __generator(this, function (_a) {
            switch (_a.label) {
                case 0: return [4 /*yield*/, fetch(url)];
                case 1:
                    response = _a.sent();
                    return [4 /*yield*/, response.json()];
                case 2:
                    data = _a.sent();
                    return [2 /*return*/, data.recipes];
            }
        });
    });
}
// Function to print recipes on the page
function printAllRecipes(url) {
    return __awaiter(this, void 0, void 0, function () {
        var recipeContainer_1, response, error_1;
        return __generator(this, function (_a) {
            switch (_a.label) {
                case 0:
                    _a.trys.push([0, 2, , 3]);
                    recipeContainer_1 = document.getElementById('main');
                    return [4 /*yield*/, fetchRecipesFromAPI(url)];
                case 1:
                    response = _a.sent();
                    recipeContainer_1.innerHTML = '';
                    response.forEach(function (rawRecipe) {
                        var recipe = {
                            image: rawRecipe.image,
                            name: rawRecipe.name,
                            rating: rawRecipe.rating,
                            cuisine: rawRecipe.cuisine,
                            ingredients: rawRecipe.ingredients,
                            difficulty: rawRecipe.difficulty,
                            timeTaken: rawRecipe.cookTimeMinutes + rawRecipe.prepTimeMinutes,
                            caloriesPerServing: rawRecipe.caloriesPerServing
                        };
                        var recipeElement = createRecipeElement(recipe);
                        recipeContainer_1.appendChild(recipeElement);
                    });
                    return [3 /*break*/, 3];
                case 2:
                    error_1 = _a.sent();
                    console.error('Error fetching or processing recipes:', error_1);
                    return [3 /*break*/, 3];
                case 3: return [2 /*return*/];
            }
        });
    });
}
function searchRecipes(query) {
    return __awaiter(this, void 0, void 0, function () {
        return __generator(this, function (_a) {
            printAllRecipes("https://dummyjson.com/recipes/search?q=".concat(query));
            return [2 /*return*/];
        });
    });
}
// Function to handle search
function handleSearch() {
    var searchInput = document.getElementById('search');
    var query = searchInput.value;
    if (query) {
        searchRecipes(query);
    }
}
// Event listener for search input
(_a = document.getElementById('search')) === null || _a === void 0 ? void 0 : _a.addEventListener('input', handleSearch);
// Event listener for search button
(_b = document.getElementById('btn')) === null || _b === void 0 ? void 0 : _b.addEventListener('click', handleSearch);
// Initial display of recipes when the DOM content is loaded
document.addEventListener('DOMContentLoaded', function () {
    printAllRecipes('https://dummyjson.com/recipes');
});
// Function to create recipe HTML elements
function createRecipeElement(recipe) {
    var recipeDiv = document.createElement('div');
    recipeDiv.classList.add('recipe');
    var ratingDiv = document.createElement('div');
    ratingDiv.classList.add('ratings');
    ratingDiv.textContent = recipe.rating.toString();
    recipeDiv.appendChild(ratingDiv);
    // Info section
    var infoDiv = document.createElement('div');
    infoDiv.classList.add('info');
    var imgDiv = document.createElement('div');
    imgDiv.classList.add('img');
    var img = document.createElement('img');
    img.src = recipe.image;
    img.alt = 'img';
    imgDiv.appendChild(img);
    infoDiv.appendChild(imgDiv);
    var recipeNameDiv = document.createElement('div');
    recipeNameDiv.classList.add('recipe-name');
    var recipeNameH1 = document.createElement('h1');
    recipeNameH1.textContent = recipe.name;
    recipeNameDiv.appendChild(recipeNameH1);
    infoDiv.appendChild(recipeNameDiv);
    recipeDiv.appendChild(infoDiv);
    // Ingredients section
    var ingredientsDiv = document.createElement('div');
    ingredientsDiv.classList.add('ingredients');
    var ingredientsH2 = document.createElement('h2');
    ingredientsH2.textContent = 'Ingredients';
    ingredientsDiv.appendChild(ingredientsH2);
    recipe.ingredients.forEach(function (ingredient) {
        var ingredientP = document.createElement('p');
        ingredientP.classList.add('ingredient');
        ingredientP.textContent = ingredient;
        ingredientsDiv.appendChild(ingredientP);
    });
    recipeDiv.appendChild(ingredientsDiv);
    // Additional info section
    var addInfoDiv = document.createElement('div');
    addInfoDiv.classList.add('add-info');
    var cuisineDiv = document.createElement('div');
    cuisineDiv.classList.add('cuisine');
    var cuisineH2 = document.createElement('h2');
    cuisineH2.textContent = 'Cuisine';
    var cuisineText = document.createTextNode(recipe.cuisine);
    cuisineDiv.appendChild(cuisineH2);
    cuisineDiv.appendChild(cuisineText);
    addInfoDiv.appendChild(cuisineDiv);
    var difficultyDiv = document.createElement('div');
    difficultyDiv.classList.add('difficulty');
    var difficultyH2 = document.createElement('h2');
    difficultyH2.textContent = 'Difficulty';
    var difficultyText = document.createTextNode(recipe.difficulty);
    difficultyDiv.appendChild(difficultyH2);
    difficultyDiv.appendChild(difficultyText);
    addInfoDiv.appendChild(difficultyDiv);
    var timeDiv = document.createElement('div');
    timeDiv.classList.add('time');
    var timeH2 = document.createElement('h2');
    timeH2.textContent = 'Time taken';
    var timeText = document.createTextNode(recipe.timeTaken.toString());
    timeDiv.appendChild(timeH2);
    timeDiv.appendChild(timeText);
    addInfoDiv.appendChild(timeDiv);
    var caloriesDiv = document.createElement('div');
    caloriesDiv.classList.add('calories');
    var caloriesH2 = document.createElement('h2');
    caloriesH2.textContent = 'Calories';
    var caloriesText = document.createTextNode(recipe.caloriesPerServing.toString());
    caloriesDiv.appendChild(caloriesH2);
    caloriesDiv.appendChild(caloriesText);
    addInfoDiv.appendChild(caloriesDiv);
    recipeDiv.appendChild(addInfoDiv);
    return recipeDiv;
}
