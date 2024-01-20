//
//  Transition.swift
//  iosApp
//
//  Created by Jan Janovec on 20.01.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

extension AnyTransition {
    static let offestAnimationValue = UIScreen.main.bounds.width + 16
    
    static let slideForward: AnyTransition = .asymmetric(insertion: .offset(x: offestAnimationValue), removal: .offset(x: -offestAnimationValue))
    static let slideBackwards: AnyTransition = .asymmetric(insertion: .offset(x: -offestAnimationValue), removal: .offset(x: offestAnimationValue))
}
