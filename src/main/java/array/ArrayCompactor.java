package array;

import car.Car;
import rental.RentalEntry;
import user.User;

public class ArrayCompactor {

    public static void compactUserArray(User[] toCompact) {
        for (int i = 0; i < toCompact.length; i++) {
            if (toCompact[i] != null) {
                continue;
            }
            for (int j = i + 1; j < toCompact.length; j++) {
                if (toCompact[j] != null) {
                    toCompact[i] = toCompact[j];
                    toCompact[j] = null;
                    break;
                }
            }
        }
    }

    public static void compactCarArray(Car[] toCompact) {
        for (int i = 0; i < toCompact.length; i++) {
            if (toCompact[i] != null) {
                continue;
            }
            for (int j = i + 1; j < toCompact.length; j++) {
                if (toCompact[j] != null) {
                    toCompact[i] = toCompact[j];
                    toCompact[j] = null;
                    break;
                }
            }
        }
    }

    public static void compactRentalEntryArray(RentalEntry[] toCompact) {
        for (int i = 0; i < toCompact.length; i++) {
            if (toCompact[i] != null) {
                continue;
            }
            for (int j = i + 1; j < toCompact.length; j++) {
                if (toCompact[j] != null) {
                    toCompact[i] = toCompact[j];
                    toCompact[j] = null;
                    break;
                }
            }
        }
    }
}

