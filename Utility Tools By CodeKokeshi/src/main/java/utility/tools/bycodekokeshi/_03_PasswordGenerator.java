package utility.tools.bycodekokeshi;

import javafx.application.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.input.*;

import java.util.*;

public class _03_PasswordGenerator {

    // GUI Components
    @FXML TextField GeneratedPassword;
    @FXML Button Copy;
    @FXML Label Status;
    @FXML Button GeneratePassword;

    // Initialize can also be written this way without the URL and ResourceBundle or overriding the method.
    public void initialize(){
        Platform.runLater(() -> Status.requestFocus());
    }

    // This method is called when the copy button is clicked. And it copies the generated password to the clipboard.
    @FXML void CopyTo(){
        String password = GeneratedPassword.getText();
        ClipboardContent content = new ClipboardContent();
        content.putString(password);
        Clipboard.getSystemClipboard().setContent(content);
        if (!Status.getText().equals("Copied to clipboard")){
            Status.setText("Copied to clipboard");
        }
    }

    // This method is called when the "Generate Password" button is clicked. And it generates a password.
    @FXML void GeneratePasswordMethod() {
        // If the status label is not "Password Generator (English)", then set it to "Password Generator (English)".
        if (!Status.getText().equals("Password Generator (English)")){
            Status.setText("Password Generator (English)");
        }

        // We use the array "words" to generate a random word that we will use as the first part of the password.
        String word = words[new Random().nextInt(words.length)];

        // We use the method "generateRandomSymbol" to generate a random symbol that we will use as the second part of the password.
        char symbol = generateRandomSymbol();

        // We use the method "generateRandomNumber" to generate a random number that we will use as the third part or last part of the password.
        int number = generateRandomNumber();

        // We concatenate the word, symbol, and number to form the password.
        String password = word + symbol + number;

        // We set the generated password to the text field so user can modify, copy, or see it.
        GeneratedPassword.setText(password);
    }

    // This method generates a random symbol (but I only used the symbols '_', '-', and '#').
    private char generateRandomSymbol() {
        Random random = new Random();
        int[] symbolASCII = {'_', '-', '#'};

        return (char) symbolASCII[random.nextInt(symbolASCII.length)];
    }


    // This method generates a random number.
    private int generateRandomNumber() {
        Random random = new Random();
        return random.nextInt(900) + 100;
    }

    // This array contains the words that will be used to generate the password.
    // As you can see, it contains thousands of words. It was generated using my Java Array Generator.
    String[] words = {
            "Apple", "Ant", "Astronaut", "Alphabet", "Avenue", "Acrobat", "Anchor", "Adventure", "Artichoke", "Arrow",
            "Ambassador", "Aroma", "Avalanche", "Apex", "Alchemy", "Aircraft", "Astronomy", "Agenda", "Amethyst", "Architecture",
            "Athlete", "Alligator", "Aurora", "Applause", "Abyss", "Anthem", "Accomplishment", "Ancestor", "Armor", "Alchemy",
            "Aurora", "Artisan", "Avocado", "Academy", "Alloy", "Ark", "Asparagus", "Albatross", "Applause", "Asylum",
            "Ascent", "Aloe", "Alpaca", "Aristocrat", "Auburn", "Aphrodite", "Admiral", "Alchemy", "Alcove", "Ash",
            "Anvil", "Aura", "Apostle", "Arctic", "Aloha", "Arkansas", "Angler", "Alchemy", "Avenue", "Adrenaline",
            "Apricot", "Aristocracy", "Apologetic", "Alphabetical", "Auricle", "Asteroid", "Alchemy", "Affirmation", "Aquamarine", "Apostrophe",
            "Aqueduct", "Amber", "Absinthe", "Amiable", "Aviator", "Artisan", "Aloof", "Amplifier", "Antique", "Alchemy",
            "Azure", "Amulet", "Aisle", "Archipelago", "Alchemy", "Alchemy", "Aspiration", "Anatomy", "Anaconda", "Alchemy",

            "Banana", "Breeze", "Broccoli", "Ballet", "Blossom", "Balloon", "Basil", "Bubble", "Bison", "Bracelet",
            "Bakery", "Bulldog", "Barbecue", "Bamboo", "Bangle", "Bonsai", "Butterfly", "Bouquet", "Beverage", "Bamboozle",
            "Beverly", "Ballet", "Brochure", "Blizzard", "Boulevard", "Bulldozer", "Blueprint", "Brewery", "Bramble", "Bison",
            "Blouse", "Bonanza", "Bouquet", "Bumblebee", "Beacon", "Bungalow", "Bachelor", "Boutique", "Belly", "Barricade",
            "Bedroom", "Bakery", "Basil", "Buttermilk", "Bonsai", "Ballroom", "Bullseye", "Baguette", "Barrister", "Ballot",
            "Blimp", "Bronze", "Beverage", "Brick", "Bracelet", "Bulb", "Buccaneer", "Bisque", "Bonnet", "Brazen",
            "Burrow", "Bricklayer", "Backdrop", "Burlesque", "Bulwark", "Balm", "Blink", "Bobsled", "Bulldozer", "Bleach",
            "Beacon", "Bulletin", "Buckwheat", "Blizzard", "Baboon", "Barefoot", "Barrage", "Birch", "Brewery", "Beehive",
            "Bristle", "Bumblebee", "Bellboy", "Brocade", "Bracken", "Betrayal", "Bonfire", "Barrister", "Babysitter", "Bequest",

            "Cactus", "Caterpillar", "Cinnamon", "Cascade", "Cathedral", "Cerulean", "Carousel", "Carnival", "Crescent", "Cello",
            "Coral", "Chameleon", "Cocoa", "Carnivore", "Chandelier", "Citrus", "Cottage", "Cobra", "Crown", "Cricket",
            "Crescendo", "Comet", "Cherry", "Canopy", "Cathartic", "Cobblestone", "Conifer", "Cyclone", "Cavalry", "Coral",
            "Cauliflower", "Crescent", "Crusade", "Cucumber", "Canvas", "Canteen", "Cater", "Corridor", "Carnage", "Corona",
            "Cove", "Crustacean", "Calamity", "Coral", "Camel", "Cacophony", "Crest", "Cruise", "Centaur", "Ceramic",
            "Corral", "Camouflage", "Chestnut", "Coconut", "Curiosity", "Curtain", "Cobblestone", "Cuckoo", "Cathedral", "Cadence",
            "Carnelian", "Casserole", "Caprice", "Crisp", "Clamor", "Cyan", "Cappuccino", "Cherry", "Carnage", "Canine",
            "Crescendo", "Creek", "Canteen", "Curl", "Citadel", "Courtyard", "Cornucopia", "Carnivorous", "Covet", "Caterwaul",
            "Cypress", "Chiffon", "Cranberry", "Citron", "Crater", "Chameleon", "Cliff", "Cabbage", "Cobbler", "Crestfallen",
            "Citadel", "Cleft", "Cruise", "Cupboard", "Calf", "Cello", "Citrus", "Cormorant",

            "Dog", "Dance", "Dolphin", "Desk", "Dream", "Diamond", "Daisy", "Doctor", "Dragon", "Dinner",
            "Deer", "Dessert", "Drama", "Diver", "Drift", "Divide", "Dove", "Dentist", "Dress", "Dart",
            "Drum", "Dawn", "Dinosaur", "Dusk", "Dormitory", "Dynamo", "Dew", "Diploma", "Dainty", "Drizzle",
            "Dilemma", "Dread", "Dabble", "Disc", "Diligent", "Delicate", "Dapper", "Dune", "Defend", "Dynamic",
            "Daughter", "Daydream", "Decide", "Dimple", "Dialogue", "Dazzle", "Dial", "Dust", "Drench", "Diner",
            "Discount", "Deck", "Dent", "Double", "Dock", "Decay", "Delight", "Doom", "Drowsy", "Dam",
            "Dizzy", "Delve", "Debate", "Daze", "Dazzling", "Dynasty", "Droop", "Diverge", "Diplomat", "Discard",
            "Drowsiness", "Downpour", "Detour", "Disco", "Duct", "Debris", "Draw", "Dare", "Dearth", "Disguise",
            "Diminish", "Deep", "Deter", "Directory", "Draft", "Disaster", "Decipher", "Document", "Dismay", "Disallow",
            "Ditty", "Depict", "Dire",

            "Elephant", "Eagle", "Elegant", "Empire", "Enigma", "Energy", "Eclipse", "Echo", "Ethereal", "Earth",
            "Emerald", "Endless", "Eager", "Excite", "Enchant", "Elusive", "Effort", "Envelope", "Embrace", "Evoke",
            "Elbow", "Elicit", "Essence", "Evergreen", "Evolve", "Exotic", "Entrance", "Escape", "Elder", "Elastic",
            "Exquisite", "Exhale", "Entertain", "Expanse", "Eminent", "Exemplary", "Edge", "Ecstasy", "Engage", "Exploit",
            "Exhilarate", "Epiphany", "Earnest", "Endeavor", "Enlighten", "Execute", "Exalt", "Eureka", "Elevate", "Exude",
            "Exorcise", "Elude", "Exemplify", "Excess", "Echelon", "Encounter", "Exuberant", "Elfin", "Elixir", "Encourage",
            "Excursion", "Exclaim", "Equilibrium", "Espy", "Eavesdrop", "Eradicate", "Epic", "Eaves", "Encircle", "Egg",
            "Etch", "Empower", "Eggplant", "Egress", "Encroach", "Elevated", "Ephemeral", "Epoch", "Evocative", "Epitome",
            "Expedite", "Endow", "Edify", "Eddy", "Encore", "Ebb", "Elasticity", "Eccentric", "Emitter", "Elapsed",
            "Emulate",

            "Face", "Fear", "Fruit", "Fire", "Famous", "Far", "Fragile", "Fall", "Friend", "Festival",
            "Fierce", "Flood", "Freeze", "Fairy", "Fog", "Flight", "Furniture", "Frost", "Frown", "Feather",
            "Flower", "Faint", "Firm", "Forge", "Feline", "Fable", "Ferry", "Fountain", "Future", "Fish",
            "Feast", "Fade", "Fossil", "Flee", "Fiddle", "Flute", "Foil", "Fashion", "Fume", "Foresee",
            "Flicker", "Flake", "Fix", "Flair", "Fragrance", "Fulfill", "Flutter", "Faith", "Foretell", "Frosty",
            "Forsake", "Feud", "Foolish", "Fathom", "Flick", "Frenzy", "Feudal", "Firmament", "Fumble", "Foreman",
            "Foam", "Fend", "Forage", "Fidget", "Forefront", "Fatigue", "Foliage", "Flounder", "Fervent", "Falcon",
            "Frolic", "Foist", "Fickle", "Flannel", "Forgive", "Ferocious", "Foamy", "Facet", "Fright", "Fission",
            "Forecast", "Furtive",

            "Giraffe", "Guitar", "Global", "Gentle", "Graceful", "Gratitude", "Garden", "Gleaming", "Glitter", "Globe",
            "Gorgeous", "Grateful", "Generous", "Genuine", "Glowing", "Guidance", "Galaxy", "Glamorous", "Gateway", "Gesture",
            "Gracious", "Glide", "Golden", "Guardian", "Grain", "Glance", "Gallery", "Glacier", "Garnish", "Gourmet",
            "Groom", "Giddy", "Grudge", "Gravel", "Gentleman", "Giggling", "Gazelle", "Globetrotter", "Gentleness", "Glamour",
            "Gale", "Gut", "Graffiti", "Granite", "Glow", "Gymnastics", "Glimpse", "Gravy", "Grassland", "Guffaw",
            "Grapple", "Gazette", "Griddle", "Gorilla", "Gigantic", "Glimmer", "Gargantuan", "Glam", "Grind", "Gala",
            "Gentility", "Genial", "Gristle", "Gawk", "Goblin", "Grate", "Gnarled", "Glorious", "Glutton", "Geology",
            "Gallant", "Grating", "Gingerbread", "Geyser", "Gripping", "Gloom", "Gravitate", "Gemstone", "Gratify", "Godsend",
            "Gondola", "Ghostly", "Gluten", "Gruesome", "Grievance", "Gadget", "Gelatin", "Glare", "Grin",

            "Habit", "Harbor", "Hasty", "Heroic", "Handsome", "Humble", "Hunger", "Historic", "Hopeful", "Hazard",
            "Hurt", "Heavenly", "Healthy", "Hypnotic", "Highway", "Hospital", "Homage", "Hover", "Hail", "Hierarchy",
            "Heartfelt", "Haven", "Herald", "Hammer", "Honesty", "Halo", "Hitch", "Holiday", "Heirloom", "Harmony",
            "Hygiene", "Habitat", "Harp", "Hawk", "Hug", "Hybrid", "Heraldry", "Hive", "Humor", "Halcyon",
            "Huddle", "Hoard", "Harvest", "Heed", "Habitual", "Hurdle", "Hostile", "Harrowing", "Hack", "Haul",
            "Hallmark", "Haughty", "Headway", "Heritage", "Harsh", "Handicap", "Hedgehog", "Horn", "Hearth", "Hustle",
            "Hone", "Host", "Hypocrite", "Hemisphere", "Hostage", "Homestead", "Holler", "Halt", "Haze", "Holistic",
            "Hickory", "Hood", "Hoot", "Hauler", "Hypothetical", "Hiss", "Habitat", "Haystack", "Hog", "Herb",
            "Honor", "Harass", "Hoodwink", "Haggard", "Heave", "Hallucination", "Harrow", "Hollow",

            "Icicle", "Ivory", "Imagine", "Ignite", "Infinite", "Island", "Insect", "Ink", "Illumination", "Interval",
            "Ivy", "Illusion", "Inhale", "Imprint", "Icon", "Irritate", "Inspire", "Innovation", "Inherit", "Iceberg",
            "Incident", "Incline", "Integrate", "Iron", "Involve", "Income", "Impulse", "Invent", "Instruct", "Inquiry",
            "Intention", "Indulge", "Ideal", "Intuition", "Invasion", "Impression", "Instinct", "Insight", "Incorporate", "Intact",
            "Irresistible", "Interaction", "Intellect", "Influence", "Indigo", "Insomnia", "Irrigate", "Intimate", "Immortal", "Impose",
            "Intrigue", "Interpreter", "Irrelevant", "Inclusive", "Infectious", "Interpretation", "Innovative", "Interface", "Imply", "Inferno",
            "Infer", "Insulate", "Inundate", "Indoor", "Intrepid", "Initiative", "Imaginary", "Integration", "Inheritance", "Inception",
            "Incognito", "Incentive", "Immune", "Illiterate", "Invincible", "Inaugurate", "Intricate", "Innovate", "Incantation", "Insufficient",
            "Impulsive", "Incomparable", "Inconceivable", "Indomitable", "Incompatible", "Incompetent", "Inevitable", "Insightful",

            "Journey", "Joyful", "Jubilant", "Judge", "Jump", "Juxtapose", "Jellyfish", "Jazz", "Justice", "Jungle",
            "Jovial", "Jinx", "Jargon", "Jawbone", "Jacket", "Jet", "Jittery", "Jade", "Jigsaw", "Journeyman",
            "Job", "Journal", "Joust", "Joint", "Joker", "Javelin", "Jadeite", "Jawline", "Juice", "Juggler",
            "Jackal", "Jiffy", "Jukebox", "Jigsaw puzzle", "Jinxed", "Jocund", "Jot", "Jamboree", "Jalopy", "Jaunt",
            "Jeopardy", "Jolt", "Jumpy", "Jeer", "Jade green", "Jigger", "Jockey", "Jubilee", "Jobber", "Junkyard",
            "Jog", "Jamb", "Jewel", "Jagged", "Juggernaut", "Jail", "Jocose", "Jubilant", "Jerk",
            "Jut", "Jetty", "Jaded", "Juxtaposition", "Jade plant", "Jawbreaker", "Jowly", "Jolted",
            "Jocularity", "Jejune", "Juicy", "Jogger", "Jadedness", "Jadedly", "Jadeite green", "Jocosity", "Juggles",
            "Jugular", "Junk", "Jumpstart", "Jollity", "Jocundity", "Jaguar", "Jeopardize", "Jelly", "Jerkwater", "Jeeringly",

            "Kangaroo", "Keyboard", "Kaleidoscope", "Kingdom", "Knapsack", "Kettle", "Karaoke", "Keen", "Knight", "Kinetic",
            "Kindle", "Kiosk", "Kilometer", "Kite", "Karma", "Kitten", "Kernel", "Keypad", "Kilogram", "Knot",
            "Kosher", "Knit", "Kayak", "Kale", "Knead", "Kerosene", "Knickers", "Keel", "Kelp", "Kiln",
            "Kaiser", "Kick", "Kooky", "Kazoo", "Kitchen", "Knickknack", "Kibbles", "Kowtow", "Kneecap", "Kerchief",
            "Keystone", "Kowtow", "Kumquat", "Kookaburra", "Kohl", "Koan", "Kudos", "Koala", "Kabbalah", "Khaki",
            "Kvetch", "Kahuna", "Kibitz", "Kit", "Kaftan", "Kudzu", "Krypton", "Knighthood", "Kanchenjunga", "Kismet",
            "That", "Kaput", "Kith", "Keeled", "Kirtland", "Knurl", "Keepsake", "Wedge", "Kohlrabi", "Kaleidoscopic",
            "Knackered", "Kook", "Kraal", "Korma", "Ballista", "Keypunch", "Kylin", "Kabob", "Kamikaze", "Kadi",
            "Kilter", "Kurban", "Karie", "Karyotype", "Kwanzaa", "Kurt", "Knapp", "Keratin", "Kilojoule", "Kedgeree",

            "Lion", "Lamp", "Laptop", "Language", "Lemon", "Lighthouse", "Lullaby", "Luggage", "Library", "Leaf",
            "Lizard", "Ladle", "Lace", "Lipstick", "Linen", "Lagoon", "Lollipop", "Lunar", "Labyrinth", "Longitude",
            "Lounge", "Leopard", "Landmark", "Legislation", "Locomotive", "Loyalty", "Landfill", "Lust", "Lava", "Lily",
            "Leisure", "Lobster", "Lucid", "Luxury", "Leash", "Laundry", "Lecture", "Lifeguard", "Lament", "Lettuce",
            "Lull", "Loot", "Layoff", "Licorice", "Lurk", "Liberate", "Lush", "Lethal", "Longevity", "Lemonade",
            "Liberation", "Limp", "Lopsided", "Lunatic", "Litmus", "Legacy", "Lumber", "Lure", "Lute", "Lend",
            "Lingo", "Leotard", "Lug", "Livid", "Lit", "Lynx", "Luscious", "Liquid", "Lawn", "Lentil",
            "Locust", "Lasso", "Linoleum", "Lisp", "Laxative", "Lacquer", "Loner", "Lunge", "Lax", "Lick",
            "Lanyard", "Lash", "Ladybug", "Lumpy", "Loyal", "Lime", "Lacrosse",

            "Majestic", "Mountain", "Mars", "Marvelous", "Mansion", "Mosaic", "Melody", "Milkshake", "Mysterious", "Mist",
            "Migrate", "Mingle", "Mighty", "Museum", "Midnight", "Magnificent", "Majesty", "Muffin", "Mint", "Monument",
            "Mermaid", "Moonlight", "Medieval", "Meadow", "Mystical", "Mimic", "Milestone", "Maverick", "Magnetic", "Maple",
            "Monarch", "Mango", "Matrimony", "Madagascar", "Molten", "Magician", "Mystify", "Matrix", "Murder", "Mouthwatering",
            "Mischief", "Motorcycle", "Moisture", "Magnolia", "Mackerel", "Magnitude", "Monarchy", "Mandarin", "Meadowlark", "Mossy",
            "Motor", "Miraculous", "Matriarch", "Muffler", "Melancholy", "Mug", "Molecule", "Meticulous", "Moccasin", "Muster",
            "Meatball", "Mosquito", "Mural", "Milk", "Mystic", "Mindful", "Maiden", "Meditate", "Meandering", "Modest",
            "Mesmerize", "Mission", "Memorable", "Muscular", "Marshmallow", "Marine", "Mythical", "Monologue", "Missionary",

            "Nectar", "Noble", "Narrative", "Nomad", "Nautical", "Nostalgia", "Nourish", "Nemesis", "Neon", "Novel",
            "Native", "Nurture", "Nobleman", "Navigator", "Nudge", "Nebula", "Nickname", "Nutrient", "Negotiate", "Notable",
            "Noblewoman", "Neurology", "Nerve", "Nectarine", "Nanny", "Noodle", "Nuptial", "Nippy", "Navel", "Nitrogen",
            "Nasal", "Nominee", "Nymph", "Nomadic", "Niche", "Nucleus", "Neat", "Narration", "Napkin", "Narrow",
            "Nocturnal", "Numb", "Nominal", "Necessity", "Nimble", "Notorious", "Nostril", "Nightfall", "Neutral", "Novice",
            "Nod", "Notch", "Nag", "Nifty", "Nagging", "Narrator", "Nap", "Nose", "Nest", "Necessitate",
            "Nominate", "Nestle", "Nurse", "Nub", "Nascent", "Nook", "Notify", "Natal", "Nab", "Noteworthy",
            "Napoleon", "Nourishing", "Nestling",

            "October", "Ostrich", "Orchid", "Ocean", "Oasis", "Olive", "Oven", "Onion", "Oracle", "Olympics",
            "Oboe", "Omelette", "Opera", "Opulent", "Opal", "Optimism", "Organic", "Orbit", "Obsidian", "Offspring",
            "Overcome", "Outstanding", "Overjoyed", "Overflow", "Overload", "Overdue", "Overhaul", "Overtime", "Overture", "Overcast",
            "Overseas", "Overseer", "Overwhelm", "Obedient", "Object", "Observe", "Obscure", "Obstacle", "Occupy", "Oceanic",
            "Octagon", "Oddity", "Omen", "Onward", "Ongoing", "Onset", "Onslaught", "Ontario", "Onyx", "Opalescent",
            "Opaque", "Openness", "Operate", "Opponent", "Oppose", "Optimum", "Orchestrate", "Ornament", "Outbreak", "Outburst",
            "Outdo", "Outdoor", "Outgoing", "Outlandish", "Outlook", "Outnumber", "Outpace", "Outperform", "Outpost", "Outrage",
            "Outright", "Outshine", "Outskirts", "Outsmart", "Outsource", "Outstrip", "Overboard", "Overhead", "Overlap", "Overlook",
            "Overpower", "Oversee", "Oversight", "Overtake", "Overthrow", "Oyster",

            "Penguin", "Parrot", "Piano", "Pineapple", "Pumpkin", "Parachute", "Palace", "Peach", "Painting", "Poetry",
            "Pancake", "Puddle", "Polaroid", "Potion", "Pendant", "Pavement", "Puppet", "Panther", "Popcorn", "Platinum",
            "Prism", "Paddle", "Peacock", "Peppermint", "Pier", "Pyramid", "Pluto", "Pretzel", "Prophet", "Peasant",
            "Polish", "Pendulum", "Popsicle", "Prowl", "Paisley", "Pilgrim", "Placard", "Porthole", "Pajamas", "Plague",
            "Pylon", "Protein", "Petal", "Perfume", "Platoon", "Prickle", "Panic", "Pecan", "Platter", "Pedicure",
            "Periscope", "Pelt", "Plunder", "Paprika", "Pouch", "Pewter", "Peninsula", "Pneumonia", "Parole", "Parchment",
            "Perturbation", "Piercing", "Plaudit", "Panorama", "Poaching", "Patriot", "Paw print", "Pottery", "Phantom", "Peephole",
            "Preamble", "Pyjamas", "Polaris", "Plunge", "Primrose", "Pagoda", "Passageway", "Powder", "Palisade", "Pastry",
            "Portico", "Playback", "Platypus", "Plume",

            "Quick", "Quiet", "Quilt", "Quarantine", "Queen", "Quarry", "Quasar", "Quest", "Quench", "Quirky",
            "Quiz", "Quota", "Quarter", "Quicksand", "Quiver", "Quality", "Question", "Quake", "Quiche", "Quotient",
            "Quay", "Queue", "Quintessential", "Quack", "Quintet", "Quill", "Quotable", "Quantum", "Quandary", "Quorum",
            "Quartz", "Qualify", "Quirk", "Quotidian", "Quantity", "Quarrel", "Quintuplet", "Quarterly", "Quibble", "Quizzical",
            "Quinoa", "Qualification", "Quince", "Quizzing", "Quantile", "Quip", "Questionnaire", "Quartermaster", "Quicken", "Quicklime",
            "Quadrant", "Quicksilver", "Quail", "Quartet", "Dokka", "Quell", "Quasi", "Quintessence", "Quixotic", "Qualm",
            "Quickie", "Quaternion", "Quick-witted", "Quash", "Quit", "Querulous", "Quickly", "Quackery", "Quirkiness",

            "River", "Rainbow", "Rose", "Rabbit", "Rhythm", "Radiant", "Reindeer", "Rhapsody", "Rebel", "Raven",
            "Rocket", "Rapture", "Ripple", "Radiance", "Realm", "Regal", "Radiate", "Resilience", "Riddle", "Refuge",
            "Reverie", "Revelation", "Relic", "Resonate", "Rustic", "Rendezvous", "Rampant", "Retro", "Revel", "Rebirth",
            "Reverence", "Remedy", "Rustle", "Revolution", "Regalia", "Renegade", "Rust", "Reverberate", "Reverberation", "Roam",
            "Rejoice", "Riveting", "Rouse", "Reverent", "Runic", "Risque", "Replenish", "Rampage", "Rejuvenate", "Reticent",
            "Resonant", "Retreat", "Reciprocal", "Renewal", "Ravenous", "Regeneration", "Reassure", "Rigorous", "Rapport", "Resonance",
            "Renowned", "Revere", "Resplendent", "Retrograde", "Rise", "Relentless", "Revive", "Reclusive", "Resolute", "Rekindle",
            "Rally", "Resurgence", "Reminiscent", "Rhapsodic", "Relish", "Refrain", "Royalty", "Repose",

            "Sapphire", "Sunset", "Sandcastle", "Serenade", "Spectacular", "Symphony", "Safari", "Synchronize", "Serenity", "Sparkle",
            "Sardine", "Sculpture", "Siesta", "Spectacle", "Submarine", "Sorbet", "Sunrise", "Succulent", "Starlight", "Stardust",
            "Sizzle", "Skyline", "Shimmer", "Satchel", "Shenanigans", "Skyscraper", "Sphinx", "Sundial", "Sublime", "Salsa",
            "Snuggle", "Swoon", "Shimmering", "Skateboard", "Swoop", "Sapling", "Swirl", "Sorcerer", "Shadow", "Succumb",
            "Stiletto", "Sumptuous", "Snazzy", "Seashell", "Sailboat", "Snorkel", "Sparkler", "Serendipity", "Skedaddle", "Spacious",
            "Saffron", "Spontaneous", "Soothing", "Satellite", "Sequoia", "Scrumptious", "Stargazer", "Stellar", "Swan", "Savory",
            "Sorcery", "Sculpt", "Sleek", "Spellbound", "Swing", "Sunflower", "Sapient", "Scented", "Sunbeam", "Stargaze",
            "Skylight", "Stupendous", "Specter", "Stroll", "Silhouette", "Symmetrical", "Silk", "Shiver", "Scenic", "Spa",
            "Seaside", "Seductive", "Sashay", "Sprinkle",

            "Table", "Tiger", "Telephone", "Tennis", "Tree", "Trophy", "Tunnel", "Tulip", "Television", "Toast",
            "Ticket", "Tornado", "Tattoo", "Truck", "Telescope", "Turtle", "Triangle", "Tailor", "Torch", "Teapot",
            "Thunder", "Tissue", "Treadmill", "Terror", "Throne", "Temptation", "Trampoline", "Trailer", "Tango", "Testimony",
            "Twilight", "Tuxedo", "Tent", "Tuba", "Taffy", "Terrain", "Tarantula", "Tail", "Temperature", "Truffle",
            "Treasure", "Tap", "Tank", "Trilogy", "Tide", "Toothbrush", "Thermostat", "Trench", "Textbook", "Taboo",
            "Twist", "Topaz", "Test", "Target", "Train", "Tabloid", "Tyrant", "Tyrannosaurus", "Twinkle", "Trail",
            "Toaster", "Teacup", "Thunderstorm", "Tripod", "Topiary", "Tart", "Tranquility", "Triton", "Talcum", "Twirl",
            "Tractor", "Thistle", "Throttle", "Thief", "Tailgate", "Twig", "Turtleneck", "Tendril", "Tack", "Tundra",
            "Transit", "Token", "Twine", "Tyranny",

            "Umbrella", "Unicorn", "Unity", "Umbilical", "Universe", "Uplift", "Urgent", "Umbra", "Ultimatum", "Utter",
            "Understand", "Upbeat", "Uniform", "Unique", "Urchin", "Utensil", "Usher", "Uptown", "Ubiquitous", "Unveil",
            "Upward", "Utopia", "Unravel", "Upgrade", "Unanimous", "Umbilicus", "Upset", "Umbrage", "Unruly", "Ultra",
            "Unison", "Urban", "Uptight", "Unwind", "Underneath", "Unfold", "Utopian", "Utterance", "Unseen", "Ugly",
            "Uppercut", "Urge", "Unearth", "Upkeep", "Underdog", "Uncanny", "Urgency", "Unleash", "Unbelievable", "Underwater",
            "Usurp", "Unconditional", "Usual", "Unforgettable", "Umpteenth", "Unwavering", "Utmost", "Uproar", "Undulate", "Unabashed",
            "Understandable", "Uppity", "Upstanding", "Underground", "Unify", "Utilize", "Unforeseen", "Uncharted", "Unprecedented", "Unleashed",
            "Unequivocal", "Unplugged", "Unparalleled", "Upshot", "Ubiquity", "Underlie", "Undo", "Unassuming", "Upper", "Unreserved",
            "Unwitting", "Urinate", "Upheaval",

            "Van", "Vase", "Violin", "Victory", "Vacuum", "Vegetable", "Valley", "Vitamin", "Velocity", "Vest",
            "Vine", "Vehicle", "Verb", "Vacation", "Volume", "Village", "Vision", "Vote", "Vapor", "Vintage",
            "Vortex", "Vulture", "Vacant", "Vivid", "Verify", "Vagrant", "Vicinity", "Vineyard", "Vista", "Vaccinate",
            "Vibrate", "Venture", "Victorious", "Vermilion", "Visitor", "Vet", "Vinegar", "Vigor", "Veranda", "Vicarious",
            "Vocal", "Vendetta", "Vandal", "Vicar", "Vellum", "Verdict", "Vein", "Veer", "Vibration", "Veneer",
            "Vicious", "Voucher", "Venerable", "Voracious", "Vice", "Vermicelli", "Vestige", "Vegetate", "Viva", "Vividly",
            "Versatile", "Vigilant", "Vulnerable", "Vivacious", "Vigorous", "Veneration", "Valet", "Volcano", "Vex", "Vexation",
            "Vitriol", "Vital", "Vocalize", "Villain", "Vulgar", "Vestment", "Vivify", "Verbalize", "Vexing", "Vibrant",
            "Vanity", "Vibrato", "Victimize", "Verbally", "Voluptuous", "Vigorously", "Versus", "Viewpoint", "Vandalize", "Vocation",
            "Vacillate", "Vouch", "Vivisection", "Volunteer", "Viciously", "Videotape",

            "Whisper", "Wild", "Wildlife", "Wave", "Worthy", "Welcome", "Warmth", "Walnut", "Wristwatch", "Wrestler",
            "Wasp", "Wet", "Weld", "Warehouse", "Witness", "Worksheet", "Wizard", "Welfare", "Worship", "Width",
            "Whisker", "Waltz", "Witch", "Wind", "Wheel", "Wool", "Wound", "Wok", "Wobble", "Whistle",
            "Windy", "Western", "Worshipper", "Wrestle", "Whisk", "Waffle", "Wreck", "Willow", "Warrant", "Wallet",
            "Weave", "Wager", "Waddle", "Watery", "Wondrous", "Wacky", "Widget", "Wreath", "Wilt", "Whittle",
            "Wombat", "Weasel", "Wither", "Weigh", "Wriggle", "Wriggler", "Wing", "Wince", "Wail", "Wrinkle",
            "Whoop", "Wisp", "Wrangle", "Wart", "Wharf", "Whiz", "Wry", "Whim", "Warm", "Weary",
            "Wavy", "Wiggle", "Wield", "Wink",

            "Xylophone", "Xenon", "X-ray", "Xerography", "Holography", "Xenophobia", "Xylophonist", "Denial", "Geographer", "Typographical",
            "Xylene's", "Monolith", "Cyst", "Xylophonists", "Xanadu", "Heterotrophy", "Cenobitic", "Oenophile", "Holographs", "Xylitol",
            "Xerophytes", "Xylenols", "Xerophytic", "Xenophiles", "Xenodochial", "Xanthophyll", "Xylographing", "Xanthous", "Xenogenesis", "Xylyl",
            "Xenon's", "Xanthate", "Xystus", "Xenodochium", "Xylol", "Xanthine", "Xylidine", "Xanthophylls", "Xanthines", "Xylyls",
            "Xenons", "Xenogenies", "Xanthomata", "Xenograft", "Xenogenic", "Xylidines", "Xanthein", "Xylylene", "Xylophonists's", "Xenophobes",
            "Xenophobe's", "Xylophonist's", "Xyster", "Xenogeny", "Xenoliths", "Xenophobias", "Xylidin", "Xanthin", "Xanthium", "Xylographer's",
            "Xerophyte's", "Xylotomous", "Xystuses",

            "Yacht", "Yearning", "Yield", "Yoga", "Yonder", "Yawn", "Yuletide", "Youthful", "Yogurt", "Yummy",
            "Yearly", "Yielded", "Yoke", "Yaw", "Yardstick", "Yesteryear", "Yodel", "Yon", "Yammer", "Youngster",
            "Yowl", "Yesteryears", "Yapping", "Yuppie", "Yourselves", "Yeast", "Yip", "Yokel", "Yippee", "Yurt",
            "Yankee", "Yachting", "Yeoman", "Yell", "Yearbook", "Yowling", "Yearn", "Yin", "Yarn", "Yodeling",
            "Yew", "Yucca", "Yard", "Yam", "Yolk", "Yap", "Yielding",

            "Zebra", "Zoo", "Zephyr", "Zest", "Zenith", "Zodiac", "Zeal", "Zipper", "Zombie", "Zucchini",
            "Zone", "Zillion", "Zoom", "Zephyrous", "Zephyrs", "Zigzag", "Zither", "Zonal", "Zealot", "Zen",
            "Zeppelin", "Zippy", "Zircon", "Zodiacal", "Zoologist", "Zephyrlike", "Zenithal", "Zap", "Zoological", "Zirconium",
            "Zinger", "Zymotic", "Zoology", "Zoologicals", "Zebu", "Zoos", "Za", "Zoon", "Zigzagged", "Zilch",
            "Zoomorphic", "Zowie", "Zydeco", "Zirconiums", "Zoeal", "Zestful", "Zeitgeist", "Zithern", "Zigzagging", "Zygomorphic",
            "Zaibatsu", "Zoogonous", "Zoftig", "Zaddik", "Zeolite", "Zookeepers", "Zaddikim", "Zithers", "Zygomorphous", "Zoosporangium",
            "Zoosporic", "Zoster", "Zincky", "Zoonal", "Zonally", "Zoologistical", "Zoeform", "Zoogeography", "Zitherns", "Zoochore",
            "Zoonoses", "Zymolysis", "Zoonosis", "Zonary", "Zoogeographical", "Zoisite", "Zoogeographic", "Zoogeographies", "Zoochory", "Zoophily",
            "Zootoxin", "Zymogenic", "Zoolatry", "Zoopathology", "Zymase", "Zoophytes", "Zoonotic", "Zoo-geographer", "Cryptozoology", "Philanthropy",
            "Oecumenism", "Coprophagous", "fouls", "Aconite", "Zoe-trope", "Zygomorphism", "Zoo-trophy"
    };


}
